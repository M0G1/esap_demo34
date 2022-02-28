package com.example.esap_demo4.service;

import com.example.esap_demo4.jms.Sender;
import com.example.esap_demo4.model.*;

import com.example.esap_demo4.repository.GymRepository;
import com.example.esap_demo4.repository.SeasonPassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SeasonPassService {

    @Autowired
    private SeasonPassRepository seasonPassService;

    @Autowired
    private GymRepository gymRepository;

    @Autowired
    private Sender sender;

    public void create(SeasonPass seasonPass, Long gymId) {
        Gym gym = gymRepository.findById(gymId).get();
        seasonPass.setGym(gym);
        seasonPassService.save(seasonPass);
        //  ====== SENDER ======
        sender.sendInsert(seasonPass, seasonPass.getId());
    }

    public SeasonPass get(Long id) {
        return seasonPassService.findById(id).get();
    }

    public List<SeasonPass> getAll() {
        return seasonPassService.findAll().stream().sorted(Comparator.comparing(SeasonPass::getFullName)).collect(Collectors.toList());
    }

    public void update(Long seasonPassId, SeasonPass newSeasonPass, Long gymId) {
        SeasonPass seasonPass = seasonPassService.findById(seasonPassId).get();
        SeasonPass temp = new SeasonPass(seasonPass.getFullName(), seasonPass.getDurationM(), seasonPass.getGym());
        seasonPass.setDurationM(newSeasonPass.getDurationM());
        seasonPass.setFullName(newSeasonPass.getFullName());
        Gym gym = gymRepository.findById(gymId).get();
        seasonPass.setGym(gym);
        seasonPassService.save(seasonPass);
        //  ====== SENDER ======
        sender.sendUpdate(temp, seasonPass, seasonPassId);
    }

    public void delete(Long seasonPassId) {
        SeasonPass seasonPass = seasonPassService.findById(seasonPassId).get();
        seasonPass.getGym().getPasses().remove(seasonPass);
        seasonPassService.deleteById(seasonPassId);
        //  ====== SENDER ======
        sender.sendDelete(seasonPass, seasonPassId);
    }

}

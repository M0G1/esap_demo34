package com.example.esap_demo4.service;

import com.example.esap_demo4.jms.Sender;
import com.example.esap_demo4.model.*;
import com.example.esap_demo4.repository.GymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional // ano repo is deleted
public class GymService {

    @Autowired
    private GymRepository gymRepository;
    @Autowired
    private Sender sender;

    public void create(Gym gym) {
        gymRepository.save(gym);
        //  ====== SENDER ======
        sender.sendInsert(gym, gym.getId());
    }

    public Gym get(Long id) {
        return gymRepository.findById(id).get();
    }

    public List<Gym> getAll() {
        return gymRepository.findAll().stream().sorted(Comparator.comparing(Gym::getAddress)).collect(Collectors.toList());
    }

    public void update(Long gymId, Gym newGym) {
        Gym gym = gymRepository.findById(gymId).get();
        Gym temp = new Gym(gym.getAddress(), gym.getOpenTime(), gym.getGymNum(), gym.getPasses());
        gym.setGymNum(newGym.getGymNum());
        gym.setAddress(newGym.getAddress());
        gym.setOpenTime(newGym.getOpenTime());
        gymRepository.save(gym);
        //  ====== SENDER ======
        sender.sendUpdate(temp, gym, gymId);
    }

    public void delete(Long gymId) {
        Gym gym = gymRepository.findById(gymId).get();
        gymRepository.deleteById(gymId);
        //  ====== SENDER ======
        gym.getPasses().forEach(seasonPass -> sender.sendDelete(seasonPass, seasonPass.getId()));
        sender.sendDelete(gym, gymId);

    }

    public List<SeasonPass> getSeasonPasses(Long id) {
        Gym gym = gymRepository.findById(id).get();
        return gym.getPasses().stream().sorted(Comparator.comparing(SeasonPass::getFullName)).collect(Collectors.toList());
    }
}
package com.example.esap_demo4.service;

import com.example.esap_demo4.model.DBChange;
import com.example.esap_demo4.repository.DBChangeRepository;
import com.example.esap_demo4.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DBChangeService {

    @Autowired
    DBChangeRepository dbChangeRepository;

    public void create(DBChange dbChange) {
        dbChangeRepository.save(dbChange);
    }

    public DBChange get(Long id) {
        return dbChangeRepository.findById(id).get();
    }

    public List<DBChange> getAll() {
        return dbChangeRepository.findAll().stream().sorted(Comparator.comparing(DBChange::getType)).collect(Collectors.toList());
    }

    public void update(Long id, DBChange newDBChange) {
        DBChange dbChange = dbChangeRepository.findById(id).get();
        dbChange.setEntityId(newDBChange.getEntityId());
        dbChange.setOperation(newDBChange.getOperation());
        dbChange.setNewEntityString(newDBChange.getNewEntityString());
        dbChange.setOldEntityString(newDBChange.getOldEntityString());
        dbChange.setType(newDBChange.getType());

        dbChangeRepository.save(dbChange);
    }

    public void delete(Long id) {
        dbChangeRepository.deleteById(id);
    }
}

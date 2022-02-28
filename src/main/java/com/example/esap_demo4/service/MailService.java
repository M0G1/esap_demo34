package com.example.esap_demo4.service;

import com.example.esap_demo4.model.Mail;
import com.example.esap_demo4.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MailService {

    @Autowired
    MailRepository mailRepository;

    public void create(Mail mail) {
        mailRepository.save(mail);
    }

    public Mail get(Long id) {
        return mailRepository.findById(id).get();
    }

    public List<Mail> getAll() {
        return mailRepository.findAll().stream().sorted(Comparator.comparing(Mail::getEmail)).collect(Collectors.toList());
    }

    public void update(Long id, Mail newMail) {
        Mail mail = mailRepository.findById(id).get();
        mail.setMessage(newMail.getMessage());
        mail.setEmail(newMail.getEmail());
        mailRepository.save(mail);
    }

    public void delete(Long id) {
        mailRepository.deleteById(id);
    }

}

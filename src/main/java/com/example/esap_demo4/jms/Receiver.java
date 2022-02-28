package com.example.esap_demo4.jms;

import com.example.esap_demo4.model.DBChange;
import com.example.esap_demo4.model.Mail;
import com.example.esap_demo4.service.DBChangeService;
import com.example.esap_demo4.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @Autowired
    private DBChangeService dbChangeService;
    @Autowired
    private MailService mailService;

    @JmsListener(destination = "dbChange")
    public void receiveMessage(DBChange dbChange) {
        // insert into db
        dbChangeService.create(dbChange);
        // send mail ))
        Mail mail = new Mail("myslim2000@", dbChange.toString());
        mailService.create(mail);
        System.out.println(mail);
    }
}

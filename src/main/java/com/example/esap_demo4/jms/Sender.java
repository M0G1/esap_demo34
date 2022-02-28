package com.example.esap_demo4.jms;

import com.example.esap_demo4.model.DBChange;
import com.example.esap_demo4.model.Mail;
import com.example.esap_demo4.service.DBChangeService;
import com.example.esap_demo4.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.jms.core.JmsTemplate;

@Component
public class Sender {
    @Autowired
    private JmsTemplate jmsTemplate;

    private final String dbChangeDestination = "dbChange";

    public void sendInsert(Object entity, Long entityId) {
        jmsTemplate.convertAndSend(dbChangeDestination, new DBChange(DBChange.Operation.INSERT, null, entity, entityId));
    }

    public void sendUpdate(Object oldEntity, Object newEntity, Long entityId) {
        jmsTemplate.convertAndSend(dbChangeDestination, new DBChange(DBChange.Operation.UPDATE, oldEntity, newEntity, entityId));
    }

    public void sendDelete(Object entity, Long entityId) {
        jmsTemplate.convertAndSend(dbChangeDestination, new DBChange(DBChange.Operation.DELETE, entity, null, entityId));
    }

}

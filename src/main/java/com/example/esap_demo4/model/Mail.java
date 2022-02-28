package com.example.esap_demo4.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String message;

    public Mail() {
    }

    public Mail(String emailAddress, String message) {
        this.email = emailAddress;
        this.message = message;
    }
}

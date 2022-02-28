package com.example.esap_demo4.repository;


import com.example.esap_demo4.model.Gym;
import com.example.esap_demo4.model.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailRepository extends JpaRepository<Mail, Long> {
}

package com.khalil.ebank.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    // Mock implementation for demo purposes (No SMTP required)
    // fait semblant d'envoyer un mail, j'ai pas de serveur smtp sous la main mdr

    public void sendEmail(String to, String subject, String text) {
        System.out.println("----- MOCK EMAIL START -----");
        System.out.println("To: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Body: \n" + text);
        System.out.println("----- MOCK EMAIL END -----");
    }
}

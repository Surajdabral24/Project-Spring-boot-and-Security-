package com.example.peter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String toEMail,String subject,String body){
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("surajdabral24@gmail.com");
            message.setTo(toEMail);
            message.setText(body);
            message.setSubject(subject);


            javaMailSender.send(message);
            System.out.println("mail send successfully......");

        }catch (RuntimeException runtimeException){
            runtimeException.printStackTrace();
        }
        catch (Exception e){
            e.getMessage();
            System.err.println("Failed to send mail: " + e.getMessage());
        }

    }
}

package com.example.peter.service;


import com.example.peter.config.TwilioConfig;
import com.example.peter.dto.OtpDto;
import com.example.peter.emun.Status;
import com.example.peter.entity.User;
import com.example.peter.repository.UserRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class LoginService {

    @Autowired
    private TwilioConfig twilioConfig;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    private String otp;
    private static final int EXPIRATION_TIME = 300;


    public OtpDto sendOtp(String phoneNumber) {

        Optional<User> optionalUser = this.userRepository.findByEmailOrPhoneNumber(phoneNumber,phoneNumber);
//        User user = this.userRepository.findByPhoneNumber(phoneNumber);

        if(optionalUser.isPresent()){

            if ( phoneNumber.equals(optionalUser.get().getPhoneNumber())) {
                try{
                    Twilio.init(twilioConfig.getAccountSID(), twilioConfig.getAuthToken());
                    PhoneNumber to = new PhoneNumber(phoneNumber);
                    PhoneNumber from = new PhoneNumber(twilioConfig.getPhoneNumber());
                    otp = generateOtp();
                    Message.creator(to, from, "your otp is:" + otp).create();
                    OtpDto dto = new OtpDto();
                    dto.setMsg("otp sent successfully");
                    dto.setStatus(Status.CREATED);
                    return dto;
                }catch (Exception e){
                    e.printStackTrace();
                }

            } else if(phoneNumber.equals(optionalUser.get().getEmail())){
                    sendMail();
                OtpDto dto = new OtpDto();
                dto.setMsg("otp sent successfully");
                dto.setStatus(Status.CREATED);
                return dto;

            }else{
                OtpDto dto = new OtpDto();
                dto.setMsg("please provide valid input");
                dto.setStatus(Status.FAILED);
                return dto;

            }

        }
        OtpDto dto = new OtpDto();
        dto.setMsg("user not registered with this credentails");
        dto.setStatus(Status.FAILED);
        return dto;

    }


    long expirationTime;

    public String generateOtp() {
        int num = new Random().nextInt(9999) + 1000;
        expirationTime = System.currentTimeMillis() + (EXPIRATION_TIME * 1000);
        return String.valueOf(num);
    }



    public String validateOtp(String Otp) {
        if(Otp != null){

            if (this.otp.equals(Otp) && expirationTime > System.currentTimeMillis()) {
                return "valid otp";
            } else {
                return "invalid otp";
            }
        }else{
            return "please enter otp";
        }

    }


    public String changePassword(String phoneNumber,String oldPassword,String newPassword){
            User user = this.userRepository.findByPhoneNumber(phoneNumber);
            if(passwordEncoder.matches(oldPassword,user.getPassword())){
                user.setPassword(passwordEncoder.encode(newPassword));
                this.userRepository.save(user);
                return "password changed";
            }else{
                return "password not matched";
            }

    }




    public void sendMail(){
        emailService.sendMail("surajdabral24@gmail.com","OTP SENT" ,generateOtp());
    }

}

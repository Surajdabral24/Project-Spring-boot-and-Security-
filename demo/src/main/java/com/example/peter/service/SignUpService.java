package com.example.peter.service;

import com.example.peter.emun.Role;
import com.example.peter.emun.Status;
import com.example.peter.entity.User;
import com.example.peter.exception.CustomerUserAlreadyExists;
import com.example.peter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class SignUpService {
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;


    public String createUser(User user){
            User optionUser = userRepository.findByPhoneNumber(user.getPhoneNumber());
            if (optionUser != null){
                throw new CustomerUserAlreadyExists("user already registered with this mobile number :" + user.getPhoneNumber());
            }
            user.setPassword(encoder.encode(user.getPassword()));
            user.setStatus(Status.CREATED);
            user.setRole(Role.User);
            this.userRepository.save(user);
            return "user register";
        }


        public String createAdmin(User user) {

            user.setPassword(encoder.encode(user.getPassword()));
            user.setStatus(Status.CREATED);
            user.setRole(Role.Admin);
            this.userRepository.save(user);
            return "Admin register";
        }

        public String createSubadmin(User user){

            user.setPassword(encoder.encode(user.getPassword()));
            user.setStatus(Status.CREATED);
            user.setRole(Role.Subadmin);
            this.userRepository.save(user);
            return "Subadmin register";

        }

    }


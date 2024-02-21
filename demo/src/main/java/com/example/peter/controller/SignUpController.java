package com.example.peter.controller;

import com.example.peter.constant.UserConstant;
import com.example.peter.entity.User;
import com.example.peter.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {


    @Autowired
    private SignUpService signUpService;

    @Autowired
    private UserConstant userConstant;


    @PostMapping("/signUp/user")              //anyone can access this endpoint but registered only user here..........................................
    public ResponseEntity<String> createUser(@RequestBody User user){
        try{
            String str = this.signUpService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(userConstant.MESSAGE_201);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(userConstant.MESSAGE_205);
        }
    }


    @PostMapping("/signUp/admin")
    @PreAuthorize("hasAuthority('SuperAdmin')")   // for access please create super  admin in database....................................................
    public ResponseEntity<String> createAdmin(@RequestBody User user){
        try{
            String str = this.signUpService.createAdmin(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(userConstant.MESSAGE_201);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(userConstant.MESSAGE_205);
        }
    }


    @PostMapping("/signUp/subadmin")
    @PreAuthorize("hasAuthority('Admin')")                //  admin can access this endpoint ...........................................
    public ResponseEntity<String> createSubadmin(@RequestBody User user){
        try{
            String str = this.signUpService.createSubadmin(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(userConstant.MESSAGE_201);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(userConstant.MESSAGE_205);
        }
    }

}

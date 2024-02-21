package com.example.peter.controller;


import com.example.peter.constant.UserConstant;
import com.example.peter.dto.AuthRequest;
import com.example.peter.dto.OtpDto;
import com.example.peter.filter.JwtService;
import com.example.peter.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;



    @PostMapping("/login")
    public String AuthenticationAndGetToken(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
        if (authentication.isAuthenticated()){
            return jwtService.generateToken(authRequest.getUsername());

        }else {
            throw new UsernameNotFoundException("invalid token");
        }
    }




    @PostMapping("/forgetPassword/{phoneNumber}")
    public ResponseEntity<OtpDto> sendOtp(@PathVariable String phoneNumber){
        try {
            OtpDto dto = loginService.sendOtp(phoneNumber);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


    @PostMapping("/resend/{phoneNumber}")
    public ResponseEntity<OtpDto> resendOtp(@PathVariable String phoneNumber){
        try {
            OtpDto dto = loginService.sendOtp(phoneNumber);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


    @PostMapping("/validate/{otp}")
    public ResponseEntity<String> validateOtp(@PathVariable("otp") String otp){
        try{
            String str  = this.loginService.validateOtp(otp);
            return ResponseEntity.status(HttpStatus.OK).body(str);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(UserConstant.MESSAGE_205);
        }

    }


    @GetMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestParam String phoneNumber,@RequestParam String oldPassword,@RequestParam String newPassword){
        try {
            String str = this.loginService.changePassword(phoneNumber, oldPassword, newPassword);
            return ResponseEntity.status(HttpStatus.OK).body(str);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }



}

package com.example.peter.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Configuration
@ConfigurationProperties(prefix = "twilio")
public class TwilioConfig {

    private String accountSID;
    private String authToken;
    private String phoneNumber;

    public TwilioConfig(String accountSID, String authToken, String phoneNumber) {
        this.accountSID = accountSID;
        this.authToken = authToken;
        this.phoneNumber = phoneNumber;
    }

    public TwilioConfig() {
    }

    public String getAccountSID() {
        return accountSID;
    }

    public void setAccountSID(String accountSID) {
        this.accountSID = accountSID;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}

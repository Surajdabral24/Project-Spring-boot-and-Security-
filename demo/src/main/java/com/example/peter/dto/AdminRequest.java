package com.example.peter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdminRequest {

    private long id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private  String role;
}

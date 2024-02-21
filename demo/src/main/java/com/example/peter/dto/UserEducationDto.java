package com.example.peter.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEducationDto {

    private String courseName;
    private String certificateUrl;      // file url
    private String describeExperience;
    private String cvUrl;   // file url
    private String skills;
    @OneToOne
    @JsonBackReference
    private UserDto user;

}

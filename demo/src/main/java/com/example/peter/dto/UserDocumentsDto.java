package com.example.peter.dto;

import com.example.peter.entity.User;
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
public class UserDocumentsDto {

    private String nationalId;
    private String nationaFile;
    private String taxPinNumber;
    private String taxFile;
    private String policeGoodConductNumber;
    private String policeFile;
    private String letter;
    private String letterFile;
    @OneToOne
    @JsonBackReference
    private UserDto user;

}

package com.example.peter.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.io.File;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UserDocuments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long documentId;
    private String nationalId;
    private String nationaFile; // fiole name
    private String taxPinNumber;
    private String taxFile;     // file name
    private String policeGoodConductNumber;
    private String policeFile;  //file name
    private String letter;
    private String letterFile;   //file name
    @OneToOne
    @JsonBackReference
    private User user;


}
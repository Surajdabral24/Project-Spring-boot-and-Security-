package com.example.peter.dto;

import com.example.peter.emun.Status;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UserDto {

    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String email;
    private String gender;

    private String role;
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private UserDocumentsDto userDocuments;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private UserEducationDto userEducation;

}

package com.example.peter.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEducation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long EducationId;
    private String courseName;
    private String certificateUrl;      // file url
    private String describeExperience;
    private String cvUrl;   // file url
    private String skills;

    @OneToOne
    @JsonBackReference
    private User user;

}

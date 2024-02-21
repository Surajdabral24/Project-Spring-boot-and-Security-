package com.example.peter.entity;

import com.example.peter.emun.Role;
import com.example.peter.emun.Status;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;


@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(min = 2,max = 15,message = "first name should be between 2 and 15 characters" )
    private String firstname;
    @Size(min = 2,max = 15,message = "last name should be between 2 and 15 characters" )
    private String lastname;
    @Size(min = 2,max = 15,message = "username name should be between 2 and 15 characters" )
    private String username;
    @Size(min = 5,max = 15,message = "password name should be between 2 and 15 characters" )
    private String password;
    private String phoneNumber;
    private String email;
    private String gender;
    private String country;
    private String state;
    private String city;
    private String address;
    private String dob;

    @CreationTimestamp
    @Column(updatable = false)
    private Date creationTime;

    @UpdateTimestamp
    @Column(insertable = false)
    private Date updationTime;


    @Enumerated(EnumType.STRING)
    private Role role = Role.User;

    @Enumerated(EnumType.STRING)
    private Status status = Status.UNVERIFIED;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private UserDocuments userDocuments;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private UserEducation userEducation;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<WorkOrder> workOrders;



}

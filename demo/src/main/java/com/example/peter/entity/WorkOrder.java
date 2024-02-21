package com.example.peter.entity;

import com.example.peter.emun.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WorkOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long workId;
    private String serviceType;
    private String serviceProviderType;
    private String description;
    private String questionsAndInstruction;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus=OrderStatus.PENDING;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_USER_ID")
    private User user;

}

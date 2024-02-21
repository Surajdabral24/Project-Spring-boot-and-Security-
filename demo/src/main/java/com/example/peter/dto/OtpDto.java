package com.example.peter.dto;

import com.example.peter.emun.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpDto {
    private String msg;
    @Enumerated(EnumType.STRING)
    private Status status;
}

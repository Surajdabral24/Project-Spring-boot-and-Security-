package com.example.peter.dto;

import com.example.peter.emun.HttpStatus;
import com.example.peter.emun.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {
    private String apiPath;
    private HttpStatus errorStatus;
    private String errorMsg;
    private LocalDateTime date;
}

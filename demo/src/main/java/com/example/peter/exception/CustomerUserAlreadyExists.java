package com.example.peter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerUserAlreadyExists extends RuntimeException {

    public CustomerUserAlreadyExists(String msg){
        super(msg);

    }


}

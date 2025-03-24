package com.springbootacademy.pos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){ //in here we have to advise to how to handle the when call NotFoundException
        //for that use advisor 
        super(message);
    }
}

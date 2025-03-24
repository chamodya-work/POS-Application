package com.springbootacademy.pos.advisor;

import com.springbootacademy.pos.exception.NotFoundException;
import com.springbootacademy.pos.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {
    @ExceptionHandler(NotFoundException.class) //this triggers when call NotFoundException
    public ResponseEntity<StandardResponse> handleNotFoundException(NotFoundException e){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(404, "Error coming", e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }
}

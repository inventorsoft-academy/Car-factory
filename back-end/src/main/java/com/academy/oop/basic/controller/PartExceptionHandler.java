package com.academy.oop.basic.controller;

import com.academy.oop.basic.util.impl.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class PartExceptionHandler {

    private Logger logger = Logger.getLogger(PartExceptionHandler.class);

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({SQLException.class, ClassNotFoundException.class, NullPointerException.class})
    public void handleException() {
        logger.error("Part not added. You need to write tests!");
    }

}

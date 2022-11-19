package ar.utn.ecommerce.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleIllegalStateException(IllegalStateException e) {
        return e.getLocalizedMessage();
    }
}

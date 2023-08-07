package com.berrybytes.osshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorMessage handleResourceNotFound(final ResourceNotFoundException exception, final HttpServletRequest request) {
        ErrorMessage error = new ErrorMessage();
        error.setErrorMessage(exception.getMessage());
        error.callerURL(request.getRequestURI());
        error.setTimestamp(Instant.now());
        return error;
    }

    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public @ResponseBody ErrorMessage handleAlreadyExist(final AlreadyExistException exception, final HttpServletRequest request) {
        ErrorMessage error = new ErrorMessage();
        error.setErrorMessage(exception.getMessage());
        error.callerURL(request.getRequestURI());
        error.setTimestamp(Instant.now());
        return error;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorMessage handleException(final Exception exception,final HttpServletRequest request) {
        ErrorMessage error = new ErrorMessage();
        error.setErrorMessage(exception.getMessage());
        error.callerURL(request.getRequestURI());
        error.setTimestamp(Instant.now());
        return error;
    }

}

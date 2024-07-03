package com.mobiauto.sistema.handler;

import com.mobiauto.sistema.exceptions.EmailDuplicadoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        //TODO ajustar para validar message para saber a entidade e lançar a exceção especifica
        throw new EmailDuplicadoException();
    }
}
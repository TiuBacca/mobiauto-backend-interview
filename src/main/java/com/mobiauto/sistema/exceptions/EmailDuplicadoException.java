package com.mobiauto.sistema.exceptions;

public class EmailDuplicadoException extends RuntimeException {

    public EmailDuplicadoException() {
        super("Este e-mail já está cadastrado.");
    }
}
package com.mobiauto.sistema.exceptions;

public class RegistroNaoEncontradoException extends Exception {

    public RegistroNaoEncontradoException(String classe) {
        super(classe + " não foi encontrado(a).");
    }
}

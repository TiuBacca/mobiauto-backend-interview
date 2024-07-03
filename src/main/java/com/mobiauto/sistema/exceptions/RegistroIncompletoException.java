package com.mobiauto.sistema.exceptions;

public class RegistroIncompletoException extends Exception {

    public RegistroIncompletoException(String atributo) {
        super("A propriedade: " + atributo + " está faltando.");
    }
}

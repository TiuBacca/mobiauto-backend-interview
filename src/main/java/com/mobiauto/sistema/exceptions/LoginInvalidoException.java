package com.mobiauto.sistema.exceptions;

public class LoginInvalidoException extends  Exception {

    public LoginInvalidoException(){
        super("Login inválido.");
    }
}

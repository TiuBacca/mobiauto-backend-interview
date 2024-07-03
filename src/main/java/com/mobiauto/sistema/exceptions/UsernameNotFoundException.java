package com.mobiauto.sistema.exceptions;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException() {
        super("Usuário não encontrado");
    }

    public UsernameNotFoundException(String usuario ) {
        super("Usuário não encontrado: " + usuario);
    }
}

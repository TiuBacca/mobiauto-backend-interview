package com.mobiauto.sistema.exceptions;

public class UsuarioSemVinculoComRevendaException extends Exception {
    public UsuarioSemVinculoComRevendaException() {
        super("Usuário não possui vínculo com a revenda.");
    }

}

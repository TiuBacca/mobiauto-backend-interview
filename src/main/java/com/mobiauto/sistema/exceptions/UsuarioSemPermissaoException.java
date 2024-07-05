package com.mobiauto.sistema.exceptions;

public class UsuarioSemPermissaoException extends Exception{

    public UsuarioSemPermissaoException(){
        super("Este usuário não tem permissão para realizar esta operação.");
    }
}

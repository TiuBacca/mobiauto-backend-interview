package com.mobiauto.sistema.exceptions;

public class AlteracaoMesmoCargoException extends Exception {

    public AlteracaoMesmoCargoException(){
        super("Não é possível alterar o cargo para o cargo atual.");
    }
}

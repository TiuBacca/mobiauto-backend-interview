package com.mobiauto.sistema.exceptions;

public class VeiculoVendidoException extends  Exception {

    public VeiculoVendidoException() {
        super("Não é possível realizar esta operação em um veículo ja vendido.");
    }

}

package com.mobiauto.sistema.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MarcaVeiculo {

    FORD("Ford"), RENAULT("Renault"), PEUGEOT("Peugeot"), FIAT("Fiat"), NISSAN("Nissan");

    private final String descricao;
}

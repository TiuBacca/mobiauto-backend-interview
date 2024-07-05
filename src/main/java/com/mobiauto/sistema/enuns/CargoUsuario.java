package com.mobiauto.sistema.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CargoUsuario {
    PROPRIETARIO("Proprietário"),GERENTE("Gerente"),ASSISTENTE("Assistente"), ADMINISTRADOR("Administrador");

    private final String descricao;
}

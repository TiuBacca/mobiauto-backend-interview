package com.mobiauto.sistema.enuns;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusOportunidade {

NOVO("Novo"), EM_ATENDIMENTO("Em atendimento"), CONCLUIDO("Concluído");

    private final String descricao;

}

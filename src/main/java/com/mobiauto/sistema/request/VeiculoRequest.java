package com.mobiauto.sistema.request;

import com.mobiauto.sistema.enuns.MarcaVeiculo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VeiculoRequest {

    private Long id;
    private MarcaVeiculo marca;
    private String ano;
    private String versao;
    private String modelo;

}

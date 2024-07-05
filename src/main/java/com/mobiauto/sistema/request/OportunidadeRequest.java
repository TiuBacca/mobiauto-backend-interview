package com.mobiauto.sistema.request;

import liquibase.pro.packaged.A;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OportunidadeRequest {

    private Long id;

    private ClienteRequest cliente;
    private VeiculoRequest veiculo;
    private RevendaRequest revenda;
    private UsuarioRequest responsavel;

    private String motivo;

}

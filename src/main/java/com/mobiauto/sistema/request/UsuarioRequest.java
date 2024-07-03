package com.mobiauto.sistema.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioRequest {

    private Long id;
    private String nome;
    private String email;
    private String senha;

    private RevendaRequest revenda;

    private String cargo;
}

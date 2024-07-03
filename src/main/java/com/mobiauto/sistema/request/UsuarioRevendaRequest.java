package com.mobiauto.sistema.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRevendaRequest {

    private Long id;
    private UsuarioRequest usuario;
    private RevendaRequest revenda;
    private String cargo;
}

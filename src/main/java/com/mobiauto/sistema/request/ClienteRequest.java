package com.mobiauto.sistema.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteRequest {

    private Long id;
    private String nome;
    private String email;

}

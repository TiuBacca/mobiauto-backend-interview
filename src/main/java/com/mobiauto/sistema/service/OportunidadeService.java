package com.mobiauto.sistema.service;

import com.mobiauto.sistema.request.OportunidadeRequest;

public interface OportunidadeService {

    void criarOportunidade(OportunidadeRequest request ) throws  Exception;

    void redirecionarResponsavelOportunidade(OportunidadeRequest request) throws Exception;
}

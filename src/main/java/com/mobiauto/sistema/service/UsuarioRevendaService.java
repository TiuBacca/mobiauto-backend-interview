package com.mobiauto.sistema.service;

import com.mobiauto.sistema.domain.UsuarioRevenda;
import com.mobiauto.sistema.request.UsuarioRevendaRequest;

public interface UsuarioRevendaService {

    void salvarUsuarioRevenda(UsuarioRevendaRequest request) throws  Exception;
}

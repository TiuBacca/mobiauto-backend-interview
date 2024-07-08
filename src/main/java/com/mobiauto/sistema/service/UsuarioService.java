package com.mobiauto.sistema.service;

import com.mobiauto.sistema.request.UsuarioRequest;
import com.mobiauto.sistema.response.LoginResponse;

public interface UsuarioService {

    void salvarUsuario(UsuarioRequest request) throws Exception;

    LoginResponse login(UsuarioRequest request) throws  Exception;
}

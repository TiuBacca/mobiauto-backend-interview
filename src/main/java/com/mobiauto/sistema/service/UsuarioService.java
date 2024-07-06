package com.mobiauto.sistema.service;

import com.mobiauto.sistema.domain.Usuario;
import com.mobiauto.sistema.request.UsuarioRequest;

public interface UsuarioService {

    void salvarUsuario(UsuarioRequest request) throws Exception;

    Usuario getUsuarioLogado()  throws Exception;


}

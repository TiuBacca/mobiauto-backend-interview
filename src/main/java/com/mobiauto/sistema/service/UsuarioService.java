package com.mobiauto.sistema.service;

import com.mobiauto.sistema.request.UsuarioRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UsuarioService {

    void salvarUsuario(UsuarioRequest request) throws Exception;


    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}

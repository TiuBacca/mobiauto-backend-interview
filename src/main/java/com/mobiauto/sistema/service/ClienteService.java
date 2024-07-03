package com.mobiauto.sistema.service;

import com.mobiauto.sistema.request.ClienteRequest;
import com.mobiauto.sistema.domain.Cliente;

public interface ClienteService {

    Cliente salvarCliente(ClienteRequest request) throws Exception;
}

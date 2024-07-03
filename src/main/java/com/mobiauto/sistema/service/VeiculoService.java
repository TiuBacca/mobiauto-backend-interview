package com.mobiauto.sistema.service;

import com.mobiauto.sistema.domain.Veiculo;
import com.mobiauto.sistema.request.VeiculoRequest;

public interface VeiculoService {

    Veiculo salvarVeiculo(VeiculoRequest request) throws  Exception;
}

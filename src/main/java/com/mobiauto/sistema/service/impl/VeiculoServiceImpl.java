package com.mobiauto.sistema.service.impl;

import com.mobiauto.sistema.domain.Cliente;
import com.mobiauto.sistema.domain.Veiculo;
import com.mobiauto.sistema.exceptions.RegistroIncompletoException;
import com.mobiauto.sistema.exceptions.RegistroNaoEncontradoException;
import com.mobiauto.sistema.repository.VeiculoRepository;
import com.mobiauto.sistema.request.VeiculoRequest;
import com.mobiauto.sistema.service.VeiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VeiculoServiceImpl implements VeiculoService {

    private final VeiculoRepository veiculoRepository;

    @Override
    public Veiculo salvarVeiculo(VeiculoRequest request) throws Exception {
        Veiculo veiculo = (request.getId() != null) ? veiculoRepository.findById(request.getId()).orElse(new Veiculo()) : new Veiculo();

        return null;
    }

    private void validaSalvarVeiculo(VeiculoRequest request) throws Exception {
        if (request.getId() != null) {
            veiculoRepository.findById(request.getId()).orElseThrow(() -> new RegistroNaoEncontradoException("Veículo"));
        } else {
            if (request.getAno().isBlank()) {
                throw new RegistroIncompletoException("ano");
            } else if (request.getVersao().isBlank()) {
                throw new RegistroIncompletoException("versão");
            } else if (request.getModelo().isBlank()) {
                throw new RegistroIncompletoException("modelo");
            }

            if (request.getMarca() == null) {
                throw new RegistroIncompletoException("marca");
            } else {
                if (request.getMarca().getDescricao().isBlank()) {
                    throw new RegistroNaoEncontradoException("Marca veículo");
                }
            }
        }
    }
}

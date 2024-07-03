package com.mobiauto.sistema.service.impl;

import com.mobiauto.sistema.Util;
import com.mobiauto.sistema.domain.Revenda;
import com.mobiauto.sistema.exceptions.RegistroIncompletoException;
import com.mobiauto.sistema.exceptions.RegistroNaoEncontradoException;
import com.mobiauto.sistema.repository.RevendaRepository;
import com.mobiauto.sistema.request.RevendaRequest;
import com.mobiauto.sistema.service.RevendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RevendaServiceImpl implements RevendaService {

    private final RevendaRepository revendaRepository;

    @Override
    public void salvarRevenda(RevendaRequest request) throws Exception {
        validaSalvarRevenda(request);
        Revenda revenda = alimentaRevenda(request);
        revendaRepository.save(revenda);
    }

    private void validaSalvarRevenda(RevendaRequest request) throws Exception {
        if (request.getId() != null) {
            revendaRepository.findById(request.getId()).orElseThrow(() -> new RegistroNaoEncontradoException("Revenda"));
        } else {
            if (request.getNome().isBlank()) {
                throw new RegistroIncompletoException("nome");
            }

            if (request.getCnpj().isBlank()) {
                throw new RegistroIncompletoException("cnpj");
            } else {
                Util.validaCNPJ(request.getCnpj());
            }
        }
    }

    private Revenda alimentaRevenda(RevendaRequest request) {
        Revenda revenda = (request.getId() != null) ? revendaRepository.findById(request.getId()).orElse(new Revenda()) : new Revenda();

        if (request.getId() != null) {
            revenda.setId(request.getId());
        }

        if (!request.getNome().isBlank()) {
            revenda.setNome(request.getNome());
        }

        if (!request.getCnpj().isBlank()) {
            revenda.setCnpj(request.getCnpj());
        }

        return revenda;
    }
}

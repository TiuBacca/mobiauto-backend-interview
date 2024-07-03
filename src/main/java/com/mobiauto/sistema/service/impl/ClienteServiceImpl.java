package com.mobiauto.sistema.service.impl;

import com.mobiauto.sistema.domain.Cliente;
import com.mobiauto.sistema.exceptions.RegistroIncompletoException;
import com.mobiauto.sistema.exceptions.RegistroNaoEncontradoException;
import com.mobiauto.sistema.repository.ClienteRepository;
import com.mobiauto.sistema.request.ClienteRequest;
import com.mobiauto.sistema.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public Cliente salvarCliente(ClienteRequest request) throws Exception {
        validaSalvarCliente(request);

        Cliente cliente = (request.getId() != null) ? clienteRepository.findById(request.getId()).orElse(new Cliente()) : new Cliente();

        if (request.getId() != null) {
            cliente.setId(request.getId());
        }

        if (!request.getNome().isBlank()) {
            cliente.setNome(request.getNome());
        }

        if (!request.getEmail().isBlank()) {
            cliente.setEmail(request.getEmail());
        }

        return clienteRepository.save(cliente);
    }

    private void validaSalvarCliente(ClienteRequest request) throws Exception {
        if (request.getId() != null) {
            clienteRepository.findById(request.getId()).orElseThrow(() -> new RegistroNaoEncontradoException("Cliente"));
        } else {
            if (request.getNome().isBlank()) {
                throw new RegistroIncompletoException("nome cliente");
            } else if (request.getEmail().isBlank()) {
                throw new RegistroIncompletoException("e-mail cliente");
            }
        }

    }


}

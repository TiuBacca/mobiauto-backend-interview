package com.mobiauto.sistema.service.impl;

import com.mobiauto.sistema.domain.Cliente;
import com.mobiauto.sistema.domain.Oportunidade;
import com.mobiauto.sistema.exceptions.RegistroIncompletoException;
import com.mobiauto.sistema.exceptions.RegistroNaoEncontradoException;
import com.mobiauto.sistema.repository.ClienteRepository;
import com.mobiauto.sistema.repository.OportunidadeRepository;
import com.mobiauto.sistema.repository.VeiculoRepository;
import com.mobiauto.sistema.request.OportunidadeRequest;
import com.mobiauto.sistema.service.OportunidadeService;
import com.mobiauto.sistema.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OportunidadeServiceImpl implements OportunidadeService {

    private final VeiculoRepository veiculoRepository;
    private final ClienteRepository clienteRepository;
    private final ClienteService clienteService;
    private final OportunidadeRepository oportunidadeRepository;


    @Override
    public void criarOportunidade(OportunidadeRequest request) throws Exception {
        validaCriarNovaOportunidade(request);
    }

    private OportunidadeRequest validaCriarNovaOportunidade(OportunidadeRequest request) throws Exception {
        if (request.getCliente() == null) {
            throw new RegistroIncompletoException("cliente");
        } else {
            if (request.getCliente().getId() != null) {
                clienteRepository.findById(request.getCliente().getId()).orElseThrow(() -> new RegistroNaoEncontradoException("Cliente"));
            } else {
                if (request.getCliente().getNome().isBlank()) {
                    throw new RegistroIncompletoException("nome cliente");
                } else if (request.getCliente().getEmail().isBlank()) {
                    throw new RegistroIncompletoException("e-mail cliente");
                }

                Cliente clienteNovo = clienteService.salvarCliente(request.getCliente());
                request.getCliente().setId(clienteNovo.getId());
            }
        }

        if (request.getVeiculo() == null) {
            throw new RegistroIncompletoException("veículo");
        } else {
            if (request.getVeiculo().getId() != null) {
                veiculoRepository.findById(request.getVeiculo().getId()).orElseThrow(() -> new RegistroNaoEncontradoException("Veículo"));
            } else {
                if (request.getVeiculo().getMarca() == null) {
                    throw new RegistroIncompletoException("marca");
                } else {
                    if (request.getVeiculo().getMarca().getDescricao().isBlank()) {
                        throw new RegistroNaoEncontradoException("Marca veículo");
                    }
                }

                if (request.getVeiculo().getVersao().isBlank()) {
                    throw new RegistroIncompletoException("versão");
                } else if (request.getVeiculo().getAno().isBlank()) {
                    throw new RegistroIncompletoException("ano");
                }
            }
        }

        return request;
    }

    private Oportunidade alimenteOportunidade(OportunidadeRequest request) {
        Oportunidade oportunidade = (request.getId() != null) ? oportunidadeRepository.findById(request.getId()).orElse(new Oportunidade()) : new Oportunidade();


        return null;
    }
}

package com.mobiauto.sistema.service.impl;

import com.mobiauto.sistema.domain.Cliente;
import com.mobiauto.sistema.domain.Oportunidade;
import com.mobiauto.sistema.domain.Usuario;
import com.mobiauto.sistema.domain.Veiculo;
import com.mobiauto.sistema.exceptions.RegistroIncompletoException;
import com.mobiauto.sistema.exceptions.RegistroNaoEncontradoException;
import com.mobiauto.sistema.repository.*;
import com.mobiauto.sistema.request.OportunidadeRequest;
import com.mobiauto.sistema.service.ClienteService;
import com.mobiauto.sistema.service.OportunidadeService;
import com.mobiauto.sistema.service.VeiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OportunidadeServiceImpl implements OportunidadeService {

    private final VeiculoService veiculoService;
    private final VeiculoRepository veiculoRepository;
    private final ClienteRepository clienteRepository;
    private final ClienteService clienteService;
    private final RevendaRepository revendaRepository;
    private final OportunidadeRepository oportunidadeRepository;
    private final UsuarioRepository usuarioRepository;


    @Override
    public void criarOportunidade(OportunidadeRequest request) throws Exception {
        oportunidadeRepository.save(alimenteOportunidade(validaCriarNovaOportunidade(request)));
    }

    @Override
    public void redirecionarResponsavelOportunidade(OportunidadeRequest request) throws Exception {

    }

    private OportunidadeRequest validaCriarNovaOportunidade(OportunidadeRequest request) throws Exception {
        if (request.getRevenda() == null) {
            throw new RegistroIncompletoException("revenda");
        } else {
            revendaRepository.findById(request.getRevenda().getId()).orElseThrow(() -> new RegistroNaoEncontradoException("Revenda"));
        }

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

                Veiculo v = veiculoService.salvarVeiculo(request.getVeiculo());
                request.getVeiculo().setId(v.getId());
            }
        }

        return request;
    }

    private Oportunidade alimenteOportunidade(OportunidadeRequest request) throws  Exception{
        Oportunidade oportunidade = new Oportunidade();
        oportunidade.setVeiculo(veiculoRepository.findById(request.getVeiculo().getId()).get());
        oportunidade.setRevenda(revendaRepository.findById(request.getRevenda().getId()).get());
        if(request.getResponsavel() != null){
            Usuario responsavel = usuarioRepository.findById(request.getResponsavel().getId()).orElseThrow( () -> new RegistroNaoEncontradoException("Responsável"));
            oportunidade.setResponsavel(responsavel);
        }

        return oportunidade;
    }
}

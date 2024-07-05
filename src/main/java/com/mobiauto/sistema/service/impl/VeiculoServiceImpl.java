package com.mobiauto.sistema.service.impl;

import com.mobiauto.sistema.domain.Oportunidade;
import com.mobiauto.sistema.domain.Veiculo;
import com.mobiauto.sistema.enuns.StatusOportunidade;
import com.mobiauto.sistema.exceptions.RegistroIncompletoException;
import com.mobiauto.sistema.exceptions.RegistroNaoEncontradoException;
import com.mobiauto.sistema.exceptions.StatusOportunidadeInvalidaException;
import com.mobiauto.sistema.exceptions.VeiculoVendidoException;
import com.mobiauto.sistema.repository.OportunidadeRepository;
import com.mobiauto.sistema.repository.VeiculoRepository;
import com.mobiauto.sistema.request.OportunidadeRequest;
import com.mobiauto.sistema.request.VeiculoRequest;
import com.mobiauto.sistema.service.VeiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VeiculoServiceImpl implements VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final OportunidadeRepository oportunidadeRepository;

    @Override
    public Veiculo salvarVeiculo(VeiculoRequest request) throws Exception {
        validaSalvarVeiculo(request);
        Veiculo veiculo = (request.getId() != null) ? veiculoRepository.findById(request.getId()).orElse(new Veiculo()) : new Veiculo();
        if (!request.getAno().isBlank()) {
            veiculo.setAno(request.getAno());
        }

        if (!request.getVersao().isBlank()) {
            veiculo.setVersao(request.getVersao());
        }

        if (request.getModelo().isBlank()) {
            throw new RegistroIncompletoException("modelo");
        }

        if (request.getMarca() == null) {
            throw new RegistroIncompletoException("marca");
        }
        return veiculoRepository.save(veiculo);
    }

    @Override
    public void venderVeiculo(OportunidadeRequest request) throws Exception {
        validaVenderVeiculo(request);
        oportunidadeRepository.save(alimentaOportunidade(request));
    }

    private Oportunidade alimentaOportunidade(OportunidadeRequest request) throws Exception {
        Oportunidade oportunidade = oportunidadeRepository.findById(request.getId()).orElseThrow(() -> new RegistroNaoEncontradoException("Oportunidade"));
        oportunidade.setConclusao(LocalDateTime.now());
        oportunidade.setMotivo(request.getMotivo());
        oportunidade.setStatus(StatusOportunidade.CONCLUIDO);
        return oportunidade;
    }

    private void validaVenderVeiculo(OportunidadeRequest request) throws Exception {
        if (request.getId() == null) {
            throw new RegistroNaoEncontradoException("Oportunidade");
        }
        Oportunidade oportunidade = oportunidadeRepository.findById(request.getId()).orElseThrow(() -> new RegistroNaoEncontradoException("Oportunidade"));
        if (oportunidade.getStatus() != StatusOportunidade.EM_ATENDIMENTO) {
            throw new StatusOportunidadeInvalidaException("Não é possível vender um carro com uma oportunidade com status diferente de: " + StatusOportunidade.EM_ATENDIMENTO.getDescricao());
        } else if (oportunidade.getVeiculo().getVendido()){
            throw new VeiculoVendidoException();
        } else if (request.getMotivo().isBlank()){
            throw new RegistroIncompletoException("motivo");
        }


    }

    private void validaSalvarVeiculo(VeiculoRequest request) throws Exception {
        if (request.getId() != null) {
            Veiculo v = veiculoRepository.findById(request.getId()).orElseThrow(() -> new RegistroNaoEncontradoException("Veículo"));
            if (v != null && v.getVendido()) {
                throw new VeiculoVendidoException();
            }
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

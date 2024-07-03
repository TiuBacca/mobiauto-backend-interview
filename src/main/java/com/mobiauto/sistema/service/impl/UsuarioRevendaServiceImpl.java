package com.mobiauto.sistema.service.impl;

import com.mobiauto.sistema.domain.UsuarioRevenda;
import com.mobiauto.sistema.enuns.CargoUsuario;
import com.mobiauto.sistema.exceptions.RegistroIncompletoException;
import com.mobiauto.sistema.exceptions.RegistroNaoEncontradoException;
import com.mobiauto.sistema.repository.RevendaRepository;
import com.mobiauto.sistema.repository.UsuarioRepository;
import com.mobiauto.sistema.repository.UsuarioRevendaRepository;
import com.mobiauto.sistema.request.UsuarioRevendaRequest;
import com.mobiauto.sistema.service.UsuarioRevendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioRevendaServiceImpl implements UsuarioRevendaService {

    private RevendaRepository revendaRepository;
    private UsuarioRepository usuarioRepository;
    private final UsuarioRevendaRepository usuarioRevendaRepository;

    @Override
    public void salvarUsuarioRevenda(UsuarioRevendaRequest request) throws Exception {
        validaSalvarUsuarioRevenda(request);
        UsuarioRevenda userRev = (request.getId() != null) ? usuarioRevendaRepository.findById(request.getId()).orElse(new UsuarioRevenda()) : new UsuarioRevenda();

        if (request.getUsuario() != null) {
            userRev.setUsuario(usuarioRepository.findById(request.getUsuario().getId()).get());
        }

        if (request.getRevenda() != null) {
            userRev.setRevenda(revendaRepository.findById(request.getRevenda().getId()).get());
        }

        if (!request.getCargo().isBlank()) {
            userRev.setCargo(CargoUsuario.valueOf(request.getCargo()));
        }

        usuarioRevendaRepository.save(userRev);
    }

    private void validaSalvarUsuarioRevenda(UsuarioRevendaRequest request) throws Exception {
        if (request.getId() != null) {
            usuarioRevendaRepository.findById(request.getId()).orElseThrow(() -> new RegistroNaoEncontradoException("Usuario x Revenda "));
        } else {
            if (request.getUsuario() == null || request.getUsuario().getId() == null) {
                throw new RegistroIncompletoException("usuário");
            } else if (request.getRevenda() == null || request.getRevenda().getId() == null) {
                throw new RegistroIncompletoException("revenda");
            } else if (request.getCargo().isBlank()) {
                throw new RegistroIncompletoException("cargo");
            }

            revendaRepository.findById(request.getRevenda().getId()).orElseThrow(() -> new RegistroNaoEncontradoException("Revenda"));
            usuarioRepository.findById(request.getUsuario().getId()).orElseThrow(() -> new RegistroNaoEncontradoException("Usuário"));

            if (request.getCargo() != null && !request.getCargo().isBlank()) {
                try {
                    CargoUsuario.valueOf(request.getCargo());
                } catch (Exception e) {
                    throw new RegistroNaoEncontradoException("Cargo");
                }
            }
        }
    }
}

package com.mobiauto.sistema.service.impl;

import com.mobiauto.sistema.Util;
import com.mobiauto.sistema.domain.Usuario;
import com.mobiauto.sistema.domain.UsuarioRevenda;
import com.mobiauto.sistema.enuns.CargoUsuario;
import com.mobiauto.sistema.exceptions.*;
import com.mobiauto.sistema.repository.RevendaRepository;
import com.mobiauto.sistema.repository.UsuarioRepository;
import com.mobiauto.sistema.repository.UsuarioRevendaRepository;
import com.mobiauto.sistema.request.UsuarioRevendaRequest;
import com.mobiauto.sistema.service.UsuarioRevendaService;
import com.mobiauto.sistema.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioRevendaServiceImpl implements UsuarioRevendaService {

    private final RevendaRepository revendaRepository;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioRevendaRepository usuarioRevendaRepository;
    private final Util util;

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

    @Override
    public void alterarCargoUsuario(UsuarioRevendaRequest request) throws Exception {
        validaAlterarCargoUsuario(request);
        UsuarioRevenda ur = usuarioRevendaRepository.findById(request.getId()).orElseThrow(() -> new RegistroNaoEncontradoException("Usuario x Revenda"));
        ur.setCargo(CargoUsuario.valueOf(request.getCargo()));
        usuarioRevendaRepository.save(ur);
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

    private void validaPermissaoAlterarCargoUsuario(UsuarioRevendaRequest request) throws Exception {
        Usuario logado = util.getUsuarioLogado();

        if(request.getRevenda() == null || request.getRevenda().getId() == null){
            throw new RegistroIncompletoException("revenda");
        } else {
            revendaRepository.findById(request.getRevenda().getId()).orElseThrow(() -> new RegistroNaoEncontradoException("Revenda"));
        }

        UsuarioRevenda ur = usuarioRevendaRepository.findByUsuarioAndRevenda(logado.getId(), request.getRevenda().getId());
        if (ur == null){
            throw new UsuarioSemVinculoComRevendaException();
        }
        if (!(ur.getCargo() == CargoUsuario.PROPRIETARIO || ur.getCargo() == CargoUsuario.ADMINISTRADOR)) {
            throw new UsuarioSemPermissaoException();
        }

        if (ur.getCargo() == CargoUsuario.valueOf(request.getCargo())){
            throw new AlteracaoMesmoCargoException();
        }
    }

    private void validaAlterarCargoUsuario(UsuarioRevendaRequest request) throws Exception  {
        validaPermissaoAlterarCargoUsuario(request);

        if (request.getId() != null) {
            usuarioRevendaRepository.findById(request.getId()).orElseThrow(() -> new RegistroNaoEncontradoException("Usuario x Revenda"));
        } else {
            throw new RegistroIncompletoException("usuário");
        }

        if (request.getCargo().isBlank()) {
            throw new RegistroIncompletoException("cargo");
        } else {
            try {
                CargoUsuario.valueOf(request.getCargo());
            } catch (Exception e) {
                throw new RegistroNaoEncontradoException("Cargo");
            }
        }

    }
}

package com.mobiauto.sistema.service.impl;

import com.mobiauto.sistema.domain.Usuario;
import com.mobiauto.sistema.domain.UsuarioRevenda;
import com.mobiauto.sistema.enuns.CargoUsuario;
import com.mobiauto.sistema.exceptions.RegistroIncompletoException;
import com.mobiauto.sistema.exceptions.RegistroNaoEncontradoException;
import com.mobiauto.sistema.repository.RevendaRepository;
import com.mobiauto.sistema.repository.UsuarioRepository;
import com.mobiauto.sistema.repository.UsuarioRevendaRepository;
import com.mobiauto.sistema.request.UsuarioRequest;
import com.mobiauto.sistema.request.UsuarioRevendaRequest;
import com.mobiauto.sistema.service.UsuarioRevendaService;
import com.mobiauto.sistema.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRevendaService usuarioRevendaService;
    private UsuarioRevendaRepository usuarioRevendaRepository;
    private RevendaRepository revendaRepository;
    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public void salvarUsuario(UsuarioRequest request) throws Exception {
        validaSalvarUsuario(request);
        Usuario usuario = (request.getId() != null) ? usuarioRepository.findById(request.getId()).orElse(new Usuario()) : new Usuario();
        usuario = usuarioRepository.save(usuario);
        if(request.getId() == null){
            request.setId(usuario.getId());
            usuarioRevendaService.salvarUsuarioRevenda(UsuarioRevendaRequest.builder().usuario(request).revenda(request.getRevenda()).build());
        } else {
            UsuarioRevenda ur = usuarioRevendaRepository.findByUsuarioAndRevenda(request.getId(), request.getRevenda().getId());
            if (ur != null){
                CargoUsuario cargo = CargoUsuario.valueOf(request.getCargo());
                if(ur.getCargo() != cargo){
                    ur.setCargo(cargo);
                    usuarioRevendaRepository.save(ur);
                }
            }
        }
    }

    private void validaSalvarUsuario(UsuarioRequest request) throws Exception {
        if (request.getId() != null) {
            usuarioRepository.findById(request.getId()).orElseThrow(() -> new RegistroNaoEncontradoException("Usuário"));
        } else {
            if (request.getNome().isBlank()) {
                throw new RegistroIncompletoException("nome");
            } else if (request.getEmail().isBlank()) {
                throw new RegistroIncompletoException("e-mail");
            } else if (request.getSenha().isBlank()) {
                throw new RegistroIncompletoException("senha");
            } else if (request.getCargo().isBlank()) {
                throw new RegistroIncompletoException("cargo");
            } else if (request.getRevenda() == null || request.getRevenda().getId() == null) {
                throw new RegistroIncompletoException("revanda");
            }

            revendaRepository.findById(request.getRevenda().getId()).orElseThrow(() -> new RegistroNaoEncontradoException("Revenda"));
            if (request.getCargo() != null && !request.getCargo().isBlank()) {
                try {
                    CargoUsuario.valueOf(request.getCargo());
                } catch (Exception e) {
                    throw new RegistroNaoEncontradoException("Cargo");
                }
            }

        }
    }

    private Usuario alimentaUsuario(UsuarioRequest request) {
        Usuario usuario = (request.getId() != null) ? usuarioRepository.findById(request.getId()).orElse(new Usuario()) : new Usuario();

        if (!request.getNome().isBlank()) {
            usuario.setNome(request.getNome());
        }

        if (!request.getEmail().isBlank()) {
            usuario.setEmail(request.getEmail());
        }

        if (!request.getSenha().isBlank()) {
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }



        return usuario;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> usuario = (Optional<Usuario>) usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return org.springframework.security.core.userdetails.User.builder()
                .username(usuario.get().getEmail()) // Usando email como username para a autenticação
                .password(usuario.get().getSenha()) // Senha criptografada do banco de dados
                .roles("USER") // Pode adicionar roles/authorities conforme necessário
                .build();
    }

    public boolean authenticate(String email, String senha) {
        UserDetails userDetails = loadUserByUsername(email);
        return passwordEncoder.matches(senha, userDetails.getPassword());
    }

}
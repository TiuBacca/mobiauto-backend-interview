package com.mobiauto.sistema.repository;

import com.mobiauto.sistema.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Object> findByEmail(String username);
}

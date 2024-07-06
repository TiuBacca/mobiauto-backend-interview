package com.mobiauto.sistema.repository;

import com.mobiauto.sistema.domain.UsuarioRevenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRevendaRepository extends JpaRepository<UsuarioRevenda, Long> {

    @Query("select ur from UsuarioRevenda ur where ur.usuario.id =:idUser and ur.revenda.id =:idRevenda")
    UsuarioRevenda findByUsuarioAndRevenda(@Param("idUser") Long idUser, @Param("idRevenda") Long idRevenda);

    @Query("select ur from UsuarioRevenda ur where ur.usuario.id =:idUser")
    List<UsuarioRevenda> findByUsuario(@Param("idUser") Long idUser);

}

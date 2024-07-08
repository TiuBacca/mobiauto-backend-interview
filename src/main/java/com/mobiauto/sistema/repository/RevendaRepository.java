package com.mobiauto.sistema.repository;

import com.mobiauto.sistema.domain.Revenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RevendaRepository extends JpaRepository<Revenda, Long> {

    @Query(" select r from Revenda r where r.cnpj like :cnpj")
    Revenda getByCNPJ(@Param("cnpj") String cnpj);
}

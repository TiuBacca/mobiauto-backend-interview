package com.mobiauto.sistema.repository;

import com.mobiauto.sistema.domain.Revenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RevendaRepository extends JpaRepository<Revenda, Long> {

    Revenda getByCNPJ(String cnpj);
}

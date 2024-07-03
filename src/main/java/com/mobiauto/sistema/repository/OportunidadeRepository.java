package com.mobiauto.sistema.repository;

import com.mobiauto.sistema.domain.Oportunidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OportunidadeRepository extends JpaRepository<Oportunidade, Long> {
}

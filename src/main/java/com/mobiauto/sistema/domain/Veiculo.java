package com.mobiauto.sistema.domain;

import com.mobiauto.sistema.enuns.MarcaVeiculo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "veiculo", schema = "sistema")
@Entity
public class Veiculo {

    @Id
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "marca")
    private MarcaVeiculo marca;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "versao")
    private String versao;

    @Column(name = "ano")
    private String ano;

    @Column(name = "vendido")
    private Boolean vendido;
}

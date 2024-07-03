package com.mobiauto.sistema.domain;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.UniqueConstraint;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "revenda", schema = "sistema" ,  uniqueConstraints = @UniqueConstraint(columnNames = {"cnpj"}))
@Entity
public class Revenda {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cnpj")
    private String cnpj;

}

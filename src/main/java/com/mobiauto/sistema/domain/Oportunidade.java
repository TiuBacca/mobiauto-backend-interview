package com.mobiauto.sistema.domain;

import com.mobiauto.sistema.enuns.StatusOportunidade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@Table(name = "oportunidade", schema = "sistema")
@Entity
public class Oportunidade {

    @Id
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusOportunidade status;

    @ManyToOne
    @JoinColumn(name = "id_responsavel")
    private Usuario responsavel;

    @Column(name = "abertura")
    private LocalDateTime abertura;

    @Column(name = "conclusao")
    private LocalDateTime conclusao;

    @ManyToOne
    @JoinColumn(name = "id_revenda")
    private Revenda revenda;

    @Column(name = "motivo")
    private String motivo;



    public Oportunidade (){
        this.status = StatusOportunidade.NOVO;
        this.abertura = LocalDateTime.now();
    }

}

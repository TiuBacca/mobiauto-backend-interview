package com.mobiauto.sistema.domain;

import com.mobiauto.sistema.enuns.CargoUsuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "usuario_revenda", schema = "sistema",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id_usuario", "id_revenda"}))
@Entity
public class UsuarioRevenda {

    @Id
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_revenda")
    private Revenda revenda;

    // Cenário onde um usuário pode ter diferentes cargos em diferentes revendas
    @Enumerated(EnumType.STRING)
    @Column(name = "cargo")
    private CargoUsuario cargo;
}

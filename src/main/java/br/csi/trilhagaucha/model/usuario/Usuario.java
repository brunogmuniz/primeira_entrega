package br.csi.trilhagaucha.model.usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "usuarios")
@Schema(description = "Entidade que representa um usuário no sistema")
public class Usuario {

    @Id
    @Schema(description = "ID do usuário", example = "1")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(unique = true, nullable = false, updatable = false)
    private UUID uuid;

    @NonNull
    @Schema(description = "Nome do usuário", example = "Felipe")
    private String nome;

    @NonNull
    @Column(unique = true, nullable = false)
    private String email;

    @NonNull
    @JsonIgnore
    @Column(nullable = false)
    private String senha;

    @JsonIgnore
    private Boolean isAdmin = false;


    @Temporal(TemporalType.TIMESTAMP)
    private Date dataLogin;

    @PrePersist
    public void prePersist() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
        if (dataLogin == null) {
            dataLogin = new Date();
        }
    }

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
}

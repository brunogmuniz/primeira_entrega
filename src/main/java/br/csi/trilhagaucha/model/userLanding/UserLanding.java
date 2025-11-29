package br.csi.trilhagaucha.model.userLanding;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;


@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "userlanding")
@Entity
public class UserLanding {

    @Id
    @Schema(description = "ID do usuário", example = "1")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false, updatable = false)
    private UUID uuid;

    @NonNull
    @Schema(description = "Nome Completo do usuário", example = "Felipe Silva")
    private String nomeCompleto;

    @NonNull
    @Schema(description = "Primeiro Nome do usuário", example = "Felipe")
    private String primeiroNome;

    @NonNull
    @Column(unique = true, nullable = false)
    private String email;

    @NonNull
    @Column(unique = true, nullable = false)
    private String whatsapp;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    @PrePersist
    public void prePersist() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
        if (dataCadastro == null) {
            dataCadastro = new Date();
        }
    }
}

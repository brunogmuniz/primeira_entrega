package br.csi.trilhagaucha.model.checklist;


import br.csi.trilhagaucha.model.cidade.Cidade;
import br.csi.trilhagaucha.model.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "checklists")
public class Checklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean visitado = false;

    @Column(name = "data_visita")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime data_visita;

    @ManyToOne
    @JsonIgnoreProperties({"senha", "id", "email", "dataLogin"})
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "cidade_id", nullable = false)
    private Cidade cidade;
}

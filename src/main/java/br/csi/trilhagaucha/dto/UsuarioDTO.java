package br.csi.trilhagaucha.dto;


import br.csi.trilhagaucha.model.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO {

    private UUID id;
    private String email;

    public UsuarioDTO(UUID id, String email) {
        this.id = id;
        this.email = email;
    }
}

package br.csi.trilhagaucha.dto;


import br.csi.trilhagaucha.model.ROLE_USER;
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
    private ROLE_USER ROLE_USER;

    public UsuarioDTO(UUID id, String email, ROLE_USER roleUser) {
        this.id = id;
        this.email = email;
        this.ROLE_USER = roleUser;
    }
}

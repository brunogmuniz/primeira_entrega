package br.csi.trilhagaucha.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioSenhaDTO {
    private String senha;
    private UUID uuid;
}

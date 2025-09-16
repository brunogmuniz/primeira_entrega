package br.csi.trilhagaucha.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {

    @NotBlank(message = "O email não pode estar vazio")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "A senha não pode estar vazia")
    private String senha;
}
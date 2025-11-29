package br.csi.trilhagaucha.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLandingDTO {
    private String nomeCompleto;
    private String email;
    private String whatsapp;
}

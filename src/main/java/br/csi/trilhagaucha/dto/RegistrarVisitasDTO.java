package br.csi.trilhagaucha.dto;


import br.csi.trilhagaucha.model.usuario.Usuario;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrarVisitasDTO {
    private Long usuarioId;
    private Long cidadeId;
}

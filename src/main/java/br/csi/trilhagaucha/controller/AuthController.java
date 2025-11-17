package br.csi.trilhagaucha.controller;


import br.csi.trilhagaucha.dto.UsuarioDTO;
import br.csi.trilhagaucha.dto.UsuarioSenhaDTO;
import br.csi.trilhagaucha.model.usuario.Usuario;
import br.csi.trilhagaucha.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "Controller relacionado a autenticação")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/redefinirSenha")
    @Operation(summary = "Redefinir a senha", description = "Redefine a senha do usuario")
    public ResponseEntity<?> redefinirSenha(@RequestBody @Valid UsuarioSenhaDTO dto) {
        try {
            usuarioService.redefinirSenha(dto.getUuid(), dto.getSenha());
            return ResponseEntity.ok(Collections.singletonMap("mensagem", "Senha redefinida com sucesso"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("erro", e.getMessage()));
        }
    }
}

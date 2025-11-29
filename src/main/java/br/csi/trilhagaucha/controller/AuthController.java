package br.csi.trilhagaucha.controller;


import br.csi.trilhagaucha.config.JwtUtil;
import br.csi.trilhagaucha.dto.LoginRequest;
import br.csi.trilhagaucha.dto.UsuarioDTO;
import br.csi.trilhagaucha.dto.UsuarioSenhaDTO;
import br.csi.trilhagaucha.model.usuario.Usuario;
import br.csi.trilhagaucha.repository.UsuarioRepository;
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
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "Controller relacionado a autenticação")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

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

    @PostMapping("/cadastrar")
    @Operation(summary = "Cadastra usuarios")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid Usuario usuario) {
        try {
            Usuario novoUsuario = usuarioService.cadastrar(usuario);
            UsuarioDTO dto = new UsuarioDTO(novoUsuario.getUuid(), novoUsuario.getEmail(), novoUsuario.getRole_user());
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("erro", e.getMessage()));
        }
    }

    // localhost:8080/auth/login - POST
    @PostMapping("/login")
    @Operation(summary = "Loga usuarios apartir de um email e senha")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        try {
            Usuario logado = usuarioService.login(request.getEmail(), request.getSenha());

            String token = jwtUtil.gerarToken(logado.getEmail());

            Map<String, Object> resposta = new HashMap<>();
            resposta.put("usuario", new UsuarioDTO(logado.getUuid(), logado.getEmail(), logado.getRole_user()));
            resposta.put("token", token);

            return ResponseEntity.ok(resposta);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("erro", e.getMessage()));
        }
    }


}

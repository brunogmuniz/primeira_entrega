package br.csi.trilhagaucha.controller;


import br.csi.trilhagaucha.dto.LoginRequest;
import br.csi.trilhagaucha.dto.UsuarioDTO;
import br.csi.trilhagaucha.model.usuario.Usuario;
import br.csi.trilhagaucha.repository.UsuarioRepository;
import br.csi.trilhagaucha.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "Path relacionado a operações de usuários")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    // localhost:8080/usuarios/listar - GET
    @GetMapping("/listar")
    @Operation(summary = "Lista todos os usuário", description = "Retorna todos os usuário")
    public List<Usuario> listar() {return usuarioService.getUsuarios();}

    // localhost:8080/usuarios/{id} - GET
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Usuario Encontrado",
    content = @Content(mediaType = "application/json",
    schema = @Schema(implementation = UsuarioDTO.class))),
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado", content = @Content)})
    @Operation(summary = "Buscar usuário por ID", description = "Retorna um usuário correspondente ao ID fornecido")
    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id) {return usuarioService.getUsuario(id);}

    // localhost:8080/usuarios/{uiid} - GET
    @Operation(summary = "Buscar usuário por UUID", description = "Retorna um usuário correspondente ao UUID fornecido")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Usuario Encontrado", content =
    @Content(mediaType = "application/json", schema =
    @Schema(implementation = UsuarioDTO.class))),
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado", content = @Content)})
    @GetMapping("/{uiid}")
    public Usuario buscarPorUiid(@PathVariable UUID uiid) {return usuarioService.getUsuarioUUID(uiid);}

    // localhost:8080/usuarios/login - POST
    @PostMapping("/login")
    @Operation(summary = "Loga usuarios apartir de um email e senha")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        try {
            Usuario logado = usuarioService.login(request.getEmail(), request.getSenha());

            UsuarioDTO dto = new UsuarioDTO(logado.getUuid(), logado.getEmail());
            return ResponseEntity.ok(dto);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("erro", e.getMessage()));
        }
    }

    // localhost:8080/usuarios/cadastrar - POST
    @PostMapping("/cadastrar")
    @Operation(summary = "Cadastra usuarios")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid Usuario usuario) {
        try {
            Usuario novoUsuario = usuarioService.cadastrar(usuario);
            UsuarioDTO dto = new UsuarioDTO(novoUsuario.getUuid(), novoUsuario.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("erro", e.getMessage()));
        }
    }

    // localhost:8080/usuarios/{id} - DELETE
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir usuarios por ID")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        usuarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    // localhost:8080/usuarios/uuid - DELETE
    @DeleteMapping("/{uuid}")
    @Operation(summary = "Excluir usuarios por UUID")
    public ResponseEntity<?> excluirPorUuid(@PathVariable Long id) {
        usuarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}




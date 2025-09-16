package br.csi.trilhagaucha.controller;


import br.csi.trilhagaucha.dto.LoginRequest;
import br.csi.trilhagaucha.dto.UsuarioDTO;
import br.csi.trilhagaucha.model.Usuario;
import br.csi.trilhagaucha.repository.UsuarioRepository;
import br.csi.trilhagaucha.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listar")
    public List<Usuario> listar() {return usuarioService.getUsuarios();}

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id) {return usuarioService.getUsuario(id);}

    @PostMapping("/login")
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
}




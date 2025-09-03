package br.csi.trilhagaucha.controller;


import br.csi.trilhagaucha.model.Usuario;
import br.csi.trilhagaucha.repository.UsuarioRepository;
import br.csi.trilhagaucha.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    private UsuarioService usuarioService;

    @GetMapping("/listar")
    public List<Usuario> listar() {return usuarioService.getAlunos();}

}

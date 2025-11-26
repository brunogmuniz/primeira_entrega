package br.csi.trilhagaucha.controller;


import br.csi.trilhagaucha.dto.UsuarioSenhaDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/landing")
public class LandingController {

    @PostMapping
    public ResponseEntity<?> receberDados(@RequestBody @Valid UsuarioSenhaDTO dto) {

        return ResponseEntity.ok().build();
    }

}

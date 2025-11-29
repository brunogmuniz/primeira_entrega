package br.csi.trilhagaucha.controller;


import br.csi.trilhagaucha.dto.UserLandingDTO;
import br.csi.trilhagaucha.dto.UsuarioDTO;
import br.csi.trilhagaucha.dto.UsuarioSenhaDTO;
import br.csi.trilhagaucha.model.userLanding.UserLanding;
import br.csi.trilhagaucha.model.usuario.Usuario;
import br.csi.trilhagaucha.service.LandingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/landing")
public class LandingController {

    @Autowired
    private LandingService landingService;

    @PostMapping("/enviarDados")

    public ResponseEntity<?> receberDados(@RequestBody @Valid UserLandingDTO dto) {

        try {
            UserLanding user = new UserLanding();

            String nomeCompleto = dto.getNomeCompleto();
            String primeiroNome = "";

            if (nomeCompleto != null && !nomeCompleto.isBlank()) {
                primeiroNome = nomeCompleto.trim().split("\\s+")[0];
            }

            user.setNomeCompleto(nomeCompleto);
            user.setPrimeiroNome(primeiroNome);
            user.setEmail(dto.getEmail());
            user.setWhatsapp(dto.getWhatsapp());

            landingService.save(user);

            return ResponseEntity.ok().build();

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("erro", e.getMessage()));
        }
    }
}
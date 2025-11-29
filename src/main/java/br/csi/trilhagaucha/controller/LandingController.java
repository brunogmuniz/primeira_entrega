package br.csi.trilhagaucha.controller;


import br.csi.trilhagaucha.dto.UserLandingDTO;
import br.csi.trilhagaucha.model.userLanding.UserLanding;
import br.csi.trilhagaucha.service.LandingService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

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

    @Operation(summary = "Listar Todos os Usuarios ")
    @GetMapping("/listar")
    public ResponseEntity<?> listarDados() {
        return ResponseEntity.ok(landingService.findAll());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        try {
            landingService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("erro", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Editar usuario por id")
    public ResponseEntity<?> editar(@PathVariable long id, @RequestBody @Valid UserLandingDTO dto) {
        try {
            Optional<UserLanding> userExistente = landingService.findById(id);
            if (userExistente.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap("erro", "Usuário não encontrado"));
            }

            String nomeCompleto = dto.getNomeCompleto();
            String primeiroNome = "";

            if (nomeCompleto != null && !nomeCompleto.isBlank()) {
                primeiroNome = nomeCompleto.trim().split("\\s+")[0];
            }

            userExistente.get().setNomeCompleto(nomeCompleto);
            userExistente.get().setPrimeiroNome(primeiroNome);
            userExistente.get().setEmail(dto.getEmail());
            userExistente.get().setWhatsapp(dto.getWhatsapp());

            landingService.save(userExistente.orElse(null));

            return ResponseEntity.ok(userExistente);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("erro", e.getMessage()));
        }
    }

}
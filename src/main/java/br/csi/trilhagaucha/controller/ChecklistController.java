package br.csi.trilhagaucha.controller;


import br.csi.trilhagaucha.dto.RegistrarVisitasDTO;
import br.csi.trilhagaucha.dto.UsuarioDTO;
import br.csi.trilhagaucha.model.checklist.Checklist;
import br.csi.trilhagaucha.model.usuario.Usuario;
import br.csi.trilhagaucha.service.ChecklistService;
import br.csi.trilhagaucha.service.CidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/checklists")
@Tag(name = "Checklist", description = "Controller relacionado a tabela Checklist")
public class ChecklistController {
    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private ChecklistService checklistService;

    @GetMapping("/{uuid}")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Checklist Encontrada",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "Usuario não encontrado", content = @Content)})
    @Operation(summary = "Buscar Checklist por usuário UUID", description = "Retorna uma checklist correspondente ao UUID do usuario fornecido")
    public List<Checklist> findByUsuarioUUID(@PathVariable String uuid) {
        return checklistService.findByUsuarioUUID(UUID.fromString(uuid));
    }

    @PostMapping("/visitar")
    @Operation(summary = "Registrar Visita de um usuario a uma cidade", description = "Registra a visita de um usuário a uma cidade")
    public ResponseEntity<?> registrarVisita(@RequestBody RegistrarVisitasDTO dto) {
        checklistService.registrarVisita(dto.getUsuarioId(), dto.getCidadeId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/removerVisita/{cidadeId}/{usuariosId}")
    @Operation(summary = "Remove o vinculo de Usuario e Cidade (Remove Uma Visita)", description = "Remove a visita de um usuário a uma cidade")
    public ResponseEntity<Void> removerVisita(@PathVariable Long usuarioId, @PathVariable Long cidadeId) {
        checklistService.removerVisita(usuarioId, cidadeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar Checklists", description = "Retorna a lista de checklists")
    public List<Checklist> listar() {
        return checklistService.findAll();
    }

//    @GetMapping("/listarVisitadas")
//    public List<Optional> listarVisitadas(@RequestBody UUID uuid) {
//        return checklistService.listarVisitadas(uuid);
//    }

}

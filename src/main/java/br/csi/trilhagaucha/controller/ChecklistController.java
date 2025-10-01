package br.csi.trilhagaucha.controller;


import br.csi.trilhagaucha.dto.RegistrarVisitasDTO;
import br.csi.trilhagaucha.model.checklist.Checklist;
import br.csi.trilhagaucha.service.ChecklistService;
import br.csi.trilhagaucha.service.CidadeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public List<Checklist> findByUsuarioUUID(@PathVariable String uuid) {
        return checklistService.findByUsuarioUUID(UUID.fromString(uuid));
    }

    @PostMapping("/visitar/{usuarioId}/{cidadeId}")
    public ResponseEntity<?> registrarVisita(@PathVariable @Valid Long usuarioId, @PathVariable @Valid Long cidadeId) {
        System.out.println(usuarioId);
        checklistService.registrarVisita(usuarioId, cidadeId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/visitar")
    public ResponseEntity<?> registrarVisita(@RequestBody RegistrarVisitasDTO dto) {
        checklistService.registrarVisita(dto.getUsuarioId(), dto.getCidadeId());
        return ResponseEntity.ok().build();
    }

}

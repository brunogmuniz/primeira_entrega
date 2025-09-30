package br.csi.trilhagaucha.controller;


import br.csi.trilhagaucha.model.checklist.Checklist;
import br.csi.trilhagaucha.model.cidade.Cidade;
import br.csi.trilhagaucha.service.ChecklistService;
import br.csi.trilhagaucha.service.CidadeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
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


}

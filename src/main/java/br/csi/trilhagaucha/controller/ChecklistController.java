package br.csi.trilhagaucha.controller;


import br.csi.trilhagaucha.service.ChecklistService;
import br.csi.trilhagaucha.service.CidadeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Checklist", description = "Controller relacionado a tabela Checklist")
public class ChecklistController {
    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private ChecklistService checklistService;


}

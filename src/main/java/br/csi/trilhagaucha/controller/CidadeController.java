package br.csi.trilhagaucha.controller;


import br.csi.trilhagaucha.model.cidade.Cidade;
import br.csi.trilhagaucha.repository.CidadeRepository;
import br.csi.trilhagaucha.service.CidadeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cidades")
@Tag(name = "Cidades", description = "Controller relacionado a tabela cidades" )
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public List<Cidade> listar() {
        return cidadeService.listar();
    }

}

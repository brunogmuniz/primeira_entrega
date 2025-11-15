package br.csi.trilhagaucha.controller;



import br.csi.trilhagaucha.model.checklist.Checklist;
import br.csi.trilhagaucha.model.cidade.Cidade;
import br.csi.trilhagaucha.repository.ChecklistRepository;
import br.csi.trilhagaucha.repository.CidadeRepository;
import br.csi.trilhagaucha.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/stats")
@Tag(name = "Stats", description = "Path relacionado a operações de stats (graficos)")
public class StatsController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private ChecklistRepository checklistRepository;

    private final int totalCidades = 497;

    @GetMapping("/totalCidades")
    public float visitaTotalPorCidade(UUID uuid) {
        List<Cidade> cidades = cidadeRepository.findAll();
        List<Checklist> checklists = checklistRepository.findByUsuario_uuid(uuid);
        float cidadesVisitadas = checklists.size();
        return (cidadesVisitadas / totalCidades) * 100;
    }
}

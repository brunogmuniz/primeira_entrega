package br.csi.trilhagaucha.service;


import br.csi.trilhagaucha.dto.RegistrarVisitasDTO;
import br.csi.trilhagaucha.model.checklist.Checklist;
import br.csi.trilhagaucha.model.cidade.Cidade;
import br.csi.trilhagaucha.model.usuario.Usuario;
import br.csi.trilhagaucha.repository.ChecklistRepository;
import br.csi.trilhagaucha.repository.CidadeRepository;
import br.csi.trilhagaucha.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChecklistService {
    @Autowired
    private ChecklistRepository checklistRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Checklist> findByUsuarioUUID(UUID uuid) {
        return checklistRepository.findByUsuario_uuid(uuid);
    }

    public List<Checklist> findAll() {return checklistRepository.findAll();}

    public Checklist registrarVisita(Long usuarioId, Long cidadeId) {
        Cidade cidade = cidadeRepository.findById(cidadeId).orElseThrow(()-> new RuntimeException("Cidade nao encontrada"));
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(()-> new RuntimeException("Usuario nao encontrada"));

        return checklistRepository.findByUsuarioAndCidade(usuario, cidade)
                .map(checklist -> {
                    checklist.setData_visita(LocalDateTime.now());
                    checklist.setVisitado(true);
                    return checklistRepository.save(checklist);
                })
                .orElseGet(() -> {

                    Checklist checklist = new Checklist();
                    checklist.setUsuario(usuario);
                    checklist.setCidade(cidade);
                    checklist.setVisitado(true);
                    checklist.setData_visita(LocalDateTime.now());

                    return checklistRepository.save(checklist);
                });
    }

    public void removerVisita(Long usuarioId, Long cidadeId) {
        Cidade cidade = cidadeRepository.findById(cidadeId)
                .orElseThrow(() -> new RuntimeException("Cidade não encontrada"));

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Optional<Checklist> checklistOpt = checklistRepository.findByUsuarioAndCidade(usuario, cidade);

        if (checklistOpt.isPresent()) {
            Checklist checklist = checklistOpt.get();
            checklistRepository.delete(checklist);
        } else {
            throw new RuntimeException("Visita não encontrada para esse usuário e cidade");
        }
    }



    public List<Checklist> listarVisitadas(UUID uuid) {
        boolean visitado = true;
        return checklistRepository.findByUsuario_uuidAndVisitado(uuid, visitado);
    }
}

package br.csi.trilhagaucha.service;


import br.csi.trilhagaucha.model.checklist.Checklist;
import br.csi.trilhagaucha.repository.ChecklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChecklistService {
    @Autowired
    private ChecklistRepository checklistRepository;

    public List<Checklist> findByUsuarioUUID(UUID uuid) {
        return checklistRepository.findByUsuario_uuid(uuid);
    }
}

package br.csi.trilhagaucha.repository;

import br.csi.trilhagaucha.model.checklist.Checklist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChecklistRepository extends JpaRepository <Checklist, Long> {
    public List<Checklist> findByUsuario_uuid(UUID uuid);
}

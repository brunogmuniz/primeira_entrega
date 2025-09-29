package br.csi.trilhagaucha.repository;

import br.csi.trilhagaucha.model.checklist.Checklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChecklistRepository extends JpaRepository <Checklist, Long> {
}

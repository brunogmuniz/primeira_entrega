package br.csi.trilhagaucha.repository;

import br.csi.trilhagaucha.model.checklist.Checklist;
import br.csi.trilhagaucha.model.cidade.Cidade;
import br.csi.trilhagaucha.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChecklistRepository extends JpaRepository <Checklist, Long> {
    public List<Checklist> findByUsuario_uuid(UUID uuid);

    Optional<Checklist> findByUsuarioAndCidade(Usuario usuario, Cidade cidade);
}

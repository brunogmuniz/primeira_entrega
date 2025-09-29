package br.csi.trilhagaucha.repository;

import br.csi.trilhagaucha.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByUuid(UUID uuid);
    public void deleteUsuarioByUuid(UUID uuid);
}



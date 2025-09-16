package br.csi.trilhagaucha.repository;

import br.csi.trilhagaucha.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.image.BufferedImageOp;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

}



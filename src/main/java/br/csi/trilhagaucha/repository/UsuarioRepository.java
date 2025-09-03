package br.csi.trilhagaucha.repository;

import br.csi.trilhagaucha.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}

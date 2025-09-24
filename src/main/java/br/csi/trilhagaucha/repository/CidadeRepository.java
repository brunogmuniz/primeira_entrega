package br.csi.trilhagaucha.repository;

import br.csi.trilhagaucha.model.cidade.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> { }

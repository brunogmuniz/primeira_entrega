package br.csi.trilhagaucha.repository;

import br.csi.trilhagaucha.model.cidade.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    Optional<Cidade> findByRegiao(Cidade.Regiao regiao);
}

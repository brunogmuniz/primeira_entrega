package br.csi.trilhagaucha.service;

import br.csi.trilhagaucha.model.cidade.Cidade;
import br.csi.trilhagaucha.repository.CidadeRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;



    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    public List<Cidade> listarPorRegiao(Cidade.Regiao regiao) {
        return cidadeRepository.findByRegiao(regiao);
    }
    public Cidade buscarPorId(Long id) {
        Optional<Cidade> cidadeOpt = cidadeRepository.findById(id);

        if (cidadeOpt.isPresent()) {
            return cidadeOpt.get(); // encontrou, devolve
        } else {
            throw new EntityNotFoundException("Cidade com id " + id + " não encontrada");
        }
    }
}

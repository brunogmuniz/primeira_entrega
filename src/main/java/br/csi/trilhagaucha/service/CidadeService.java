package br.csi.trilhagaucha.service;

import br.csi.trilhagaucha.model.cidade.Cidade;
import br.csi.trilhagaucha.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;


    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }
}

package br.csi.trilhagaucha.controller;


import br.csi.trilhagaucha.dto.UsuarioDTO;
import br.csi.trilhagaucha.model.cidade.Cidade;
import br.csi.trilhagaucha.model.usuario.Usuario;
import br.csi.trilhagaucha.repository.CidadeRepository;
import br.csi.trilhagaucha.service.CidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/cidades")
@Tag(name = "Cidades", description = "Controller relacionado a tabela cidades" )
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    //     http://localhost:8080/cidades/listar
    @GetMapping("/listar")
    @Operation(summary = "Listar Cidades", description = "Retorna a lista de cidades")
    public List<Cidade> listar() {return cidadeService.listar();}


    //    http://localhost:8080/cidades/id/{id}
    @GetMapping("/id/{id}")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Cidade Encontrada",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Cidade.class))),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content)})
    @Operation(summary = "Buscar cidade por ID", description = "Retorna a lista de cidades por ID")
    public Cidade buscarPorid(@PathVariable Long id) {return cidadeService.buscarPorId(id);}


    //   http://localhost:8080/cidades/regiao/{regiao}
    @GetMapping("/regiao/{regiao}")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Cidades Encontradas",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Cidade.class))),
            @ApiResponse(responseCode = "404", description = "Cidades não encontradas", content = @Content)})
    @Operation(summary = "Buscar cidade por Regiao", description = "Retorna a lista de cidades por Regiao")
    public List<Cidade> buscarPorRegiao(@PathVariable Cidade.Regiao regiao){return cidadeService.listarPorRegiao(regiao);}
    
}

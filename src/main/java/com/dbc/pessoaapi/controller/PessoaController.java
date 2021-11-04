package com.dbc.pessoaapi.controller;

import com.dbc.pessoaapi.DTO.PessoaCreateDTO;
import com.dbc.pessoaapi.DTO.PessoaDTO;

import com.dbc.pessoaapi.entity.PessoaEntity;
import com.dbc.pessoaapi.service.PessoaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@RequestMapping("/pessoa")
@Validated
@Slf4j
@RequiredArgsConstructor

public class PessoaController {


    private  final PessoaService pessoaService;



    @GetMapping("/hello")
    public String hello() {
        return "Hello world!";
    }

    @ApiOperation( value = "Criar uma nova pessoa")
    @ApiResponses( value = {
            @ApiResponse(code =200, message = "Pessoa criada com sucesso"),
            @ApiResponse( code = 400, message = "Pessoa não encontrada")
    })

    @PostMapping
    public PessoaDTO create(@RequestBody @Valid PessoaCreateDTO pessoaDTO) throws Exception {
        log.info("iniciando criação da pessoa");
        PessoaDTO pessoaEntityCriado = pessoaService.create(pessoaDTO);
        log.info("pessoa criada com sucesso!");
        return pessoaEntityCriado;
    }
    @ApiOperation( value = "Lista de pessoas")
    @ApiResponses( value = {
            @ApiResponse(code =200, message = "Pessoas listadas com sucesso"),
            @ApiResponse( code = 400, message = "Pessoas não encontradas")
    })
    @GetMapping
    public List<PessoaDTO> list() {

        return pessoaService.list();
    }
    @ApiOperation( value = "Listagem de pessoas por nome")
    @ApiResponses( value = {
            @ApiResponse(code =200, message = "Pessoas listadas com sucesso"),
            @ApiResponse( code = 400, message = "Pessoas não encontradas")
    })
    @GetMapping("/byname")
    public List<PessoaDTO> listByName(@RequestParam("nome") @NotEmpty(message = "nome da pessoa não informado") String nome) {
        return pessoaService.listByName(nome);
    }



    @ApiOperation( value = "Atualizar pessoas por id")
    @ApiResponses( value = {
            @ApiResponse(code =200, message = "Pessoas atualizadas com sucesso"),
            @ApiResponse( code = 400, message = "Pessoas não encontradas")
    })
    @PutMapping("/{idPessoa}")
    public PessoaDTO update(@PathVariable("idPessoa") Integer id,
                            @RequestBody @Valid PessoaCreateDTO pessoaCreateDTO) throws Exception {
        return pessoaService.update(id, pessoaCreateDTO);
    }
    @ApiOperation( value = "deletar  pessoas por id")
    @ApiResponses( value = {
            @ApiResponse(code =200, message = "Pessoas deletadas  com sucesso"),
            @ApiResponse( code = 400, message = "Pessoas não encontradas")
    })
    @DeleteMapping("/{idPessoa}")
    public void delete(@PathVariable("idPessoa")  @Valid Integer idPessoa) throws Exception {
        log.info("deletando pessoa");
        pessoaService.delete(idPessoa);
        log.info("pessoa deletada com sucesso");

}
}
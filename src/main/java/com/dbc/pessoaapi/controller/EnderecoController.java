package com.dbc.pessoaapi.controller;
import com.dbc.pessoaapi.DTO.EnderecoDTO;
import com.dbc.pessoaapi.entity.EnderecoEntity;
import com.dbc.pessoaapi.service.EnderecoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/endereco")
@Validated
@Slf4j
@RequiredArgsConstructor
public class EnderecoController {


    private final EnderecoService enderecoService;

    @GetMapping
    public List<EnderecoDTO> list() {
        return enderecoService.list();
    }
    @GetMapping("/{idEndereco}")
    public List<EnderecoDTO> listByIdEndereco(@PathVariable("idEndereco") Integer idEndereco) {
        return enderecoService.listByIdEndereco(idEndereco);
    }
    @ApiOperation( value = "listar endereço por id da pessoa")
    @ApiResponses( value = {
            @ApiResponse(code =200, message = "Endereço listado  com sucesso"),
            @ApiResponse( code = 400, message = "endereço não encontrado")
    })
    @GetMapping("/{idPessoa}/pessoa")
    public List<EnderecoDTO> listByIdPessoa(@PathVariable("idPessoa") Integer idPessoa) {
        return enderecoService.listByIdPessoa(idPessoa);
    }
    @ApiOperation( value = "Criar um novo endereço por id")
    @ApiResponses( value = {
            @ApiResponse(code =200, message = "Endereço  criado com sucesso"),
            @ApiResponse( code = 400, message = "endereço não encontrado")
    })
    @PostMapping("/{idPessoa}")
    public EnderecoDTO create(@PathVariable ("idPessoa") Integer idPessoa,
                              @Valid @RequestBody EnderecoEntity enderecoEntity) throws Exception {
        log.info("Endereço será criado");
        EnderecoDTO endereconew =  enderecoService.update(idPessoa, enderecoEntity);
        log.info("Endereço criado com sucesso");

        return endereconew;
    }
    @ApiOperation( value = "Atualizar endereço por id")
    @ApiResponses( value = {
            @ApiResponse(code =200, message = "Endereço  atualizado com sucesso"),
            @ApiResponse( code = 400, message = "endereço não encontrado")
    })

    @PutMapping("/{idEndereco}")
    public EnderecoDTO update(@PathVariable("idEndereco") Integer idEndereco, @Valid @RequestBody EnderecoEntity enderecoEntityAtual) throws Exception {
        log.info("endereço está sendo atualizado");
        EnderecoDTO endereconew =  enderecoService.update(idEndereco, enderecoEntityAtual);
        log.info("Endereço atualizado com sucesso");

        return endereconew;
    }

    @ApiOperation( value = "Deletar um  endereço por id")
    @ApiResponses( value = {
            @ApiResponse(code =200, message = "Endereço  deletado com sucesso"),
            @ApiResponse( code = 400, message = "endereço não encontrado")
    })
    @DeleteMapping("/{idEndereco}")
    public void delete(@PathVariable("idEndereco") Integer idEndereco) throws Exception {
        log.info("deletando endereco");
        enderecoService.delete(idEndereco);
        log.info("endereco deletada com sucesso");
    }

}

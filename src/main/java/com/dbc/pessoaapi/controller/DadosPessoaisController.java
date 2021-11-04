package com.dbc.pessoaapi.controller;

import com.dbc.pessoaapi.DTO.DadosPessoaisDTO;
import com.dbc.pessoaapi.client.DadosPessoaisClient;
import com.dbc.pessoaapi.service.DadosPessoaisService;
import feign.Param;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;

@RestController
@RequestMapping("/")
@Validated
@Slf4j
@RequiredArgsConstructor
public class DadosPessoaisController {

private final DadosPessoaisClient dadosPessoaisClient;
private final  DadosPessoaisService dadosPessoaisService;



@PostMapping
    public DadosPessoaisDTO create( @RequestBody DadosPessoaisDTO dadosPessoaisDTO) throws Exception{
    return dadosPessoaisService.create(dadosPessoaisDTO);
}
 @GetMapping("/{cpf}")
   public  DadosPessoaisDTO getPorCpf(@Param("cpf") String cpf){
    return  dadosPessoaisService.getPorCpf(cpf);
 }
 @GetMapping
    public List<DadosPessoaisDTO> listar(){
    return  dadosPessoaisService.list();
 }

}

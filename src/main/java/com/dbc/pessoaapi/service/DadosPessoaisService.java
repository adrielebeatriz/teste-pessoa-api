package com.dbc.pessoaapi.service;

import com.dbc.pessoaapi.DTO.DadosPessoaisDTO;
import com.dbc.pessoaapi.client.DadosPessoaisClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DadosPessoaisService {
    private final DadosPessoaisClient dadosPessoaisClient;
    private final ObjectMapper objectMapper;

    public List<DadosPessoaisDTO> list() {
        return dadosPessoaisClient.listar();
    }
    public DadosPessoaisDTO getPorCpf(String Cpf){
        DadosPessoaisDTO dadosPessoaisDTO = dadosPessoaisClient.getPorCpf(Cpf);
        return dadosPessoaisDTO;
    }

    public DadosPessoaisDTO create(DadosPessoaisDTO dadosPessoaisDTO) throws Exception {
        return dadosPessoaisClient.post(dadosPessoaisDTO);
    }

}
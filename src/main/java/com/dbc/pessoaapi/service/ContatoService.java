package com.dbc.pessoaapi.service;
import com.dbc.pessoaapi.DTO.ContatoCreateDTO;
import com.dbc.pessoaapi.DTO.ContatoDTO;
import com.dbc.pessoaapi.entity.ContatoEntity;
import com.dbc.pessoaapi.exceptions.RegraDeNegocioException;
import com.dbc.pessoaapi.repository.ContatoRepository;
import com.dbc.pessoaapi.repository.PessoaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContatoService {
    private final ContatoRepository contatoRepository;
    private final PessoaRepository pessoaRepository;
    private final ObjectMapper objectMapper;


    public List<ContatoDTO> list(){
        return contatoRepository.list().stream()
                .map(contato -> objectMapper.convertValue(contato, ContatoDTO.class))
                .collect(Collectors.toList());


    }

    public List<ContatoDTO> listPorIdPessoa(Integer idPessoa) throws RegraDeNegocioException {
        pessoaRepository.getIdById(idPessoa);
        return contatoRepository.listByIdPessoa(idPessoa).stream()
                .map(contato -> objectMapper.convertValue(contato, ContatoDTO.class))
                .collect(Collectors.toList());
    }

    public ContatoDTO create(Integer idPessoa, ContatoEntity contatoCreateDTO) throws Exception {
        ContatoEntity contatoEntity1 = objectMapper.convertValue(contatoCreateDTO,ContatoEntity.class);
        pessoaRepository.getIdById(idPessoa);
        ContatoEntity contatoCriado = contatoRepository.create(idPessoa, contatoEntity1);
        ContatoDTO contatoDTO = objectMapper.convertValue(contatoCriado,ContatoDTO.class);
        return contatoDTO;

    }

    public ContatoDTO update(Integer idContato, ContatoCreateDTO contatoCreateDTO) throws Exception {
        ContatoEntity contatoEntity = objectMapper.convertValue(contatoCreateDTO,ContatoEntity.class);
        ContatoEntity contatoEntity1 = contatoRepository.update(idContato, contatoEntity);
        ContatoDTO dto = objectMapper.convertValue(contatoEntity1,ContatoDTO.class);
        return dto;
    }

    public void delete(Integer idContato) throws Exception {
        contatoRepository.delete(idContato);
    }
}
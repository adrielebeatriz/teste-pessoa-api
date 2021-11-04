package com.dbc.pessoaapi.service;


import com.dbc.pessoaapi.DTO.EnderecoCreateDTO;
import com.dbc.pessoaapi.DTO.EnderecoDTO;
import com.dbc.pessoaapi.entity.EnderecoEntity;
import com.dbc.pessoaapi.exceptions.RegraDeNegocioException;
import com.dbc.pessoaapi.repository.EnderecoRepository;
import com.dbc.pessoaapi.repository.PessoaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;
    private final PessoaRepository pessoaRepository;
    private final ObjectMapper objectMapper;

    public List<EnderecoDTO> list() {
        return enderecoRepository.list().stream()
                .map(endereco -> objectMapper.convertValue(endereco,EnderecoDTO.class))
                .collect(Collectors.toList());
    }


    public List<EnderecoDTO> listByIdEndereco(Integer idEndereco) {
        return enderecoRepository.listByIdEndereco(idEndereco).stream()
                .map(enderecoEntity -> objectMapper.convertValue(enderecoEntity,EnderecoDTO.class))
                .collect(Collectors.toList());
    }

    public List<EnderecoDTO> listByIdPessoa(Integer idPessoa) {
        return enderecoRepository.listByIdPessoa(idPessoa).stream()
                .map(enderecoEntity -> objectMapper.convertValue(enderecoEntity,EnderecoDTO.class))
                .collect(Collectors.toList());
    }

    public EnderecoDTO create(Integer idPessoa, EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException {
        pessoaRepository.getIdById(idPessoa);
        enderecoCreateDTO.setIdPessoa(idPessoa);
        EnderecoEntity enderecoEntity1 = objectMapper.convertValue(enderecoCreateDTO, EnderecoEntity.class);
        EnderecoEntity enderecoCriado = enderecoRepository.create(enderecoEntity1);
        EnderecoDTO dto = objectMapper.convertValue(enderecoCriado,EnderecoDTO.class);
        enderecoEntity1.setIdPessoa(idPessoa);
        return dto;
    }

    public EnderecoDTO update(Integer idEndereco, @Valid EnderecoEntity enderecoCreateDTO) throws Exception {
        pessoaRepository.getIdById(enderecoCreateDTO.getIdPessoa());
        EnderecoEntity enderecoEntity1 = objectMapper.convertValue(enderecoCreateDTO, EnderecoEntity.class);
        EnderecoEntity enderecoCriado = enderecoRepository.update(idEndereco, enderecoEntity1);
        EnderecoDTO dto = objectMapper.convertValue(enderecoCriado,EnderecoDTO.class);
        return dto;
    }

    public void delete(Integer idEndereco) throws Exception {
        enderecoRepository.delete(idEndereco);
    }
}
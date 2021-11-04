package com.dbc.pessoaapi.repository;

import com.dbc.pessoaapi.entity.PessoaEntity;
import com.dbc.pessoaapi.exceptions.RegraDeNegocioException;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
@Repository
public class PessoaRepository {
    private static List<PessoaEntity> listaPessoaEntities = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();

    public PessoaRepository() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //18/10/2020
        listaPessoaEntities.add(new PessoaEntity(COUNTER.incrementAndGet() /*1*/, "Maicon Gerardi", LocalDate.parse("10/10/1990", formatter), "12345678910", "adriele.beatriz@dbccompany.com.br"));
        listaPessoaEntities.add(new PessoaEntity(COUNTER.incrementAndGet() /*2*/, "Adriele ", LocalDate.parse("08/05/2000", formatter), "12345678911", ""));
        listaPessoaEntities.add(new PessoaEntity(COUNTER.incrementAndGet() /*3*/, "lilian", LocalDate.parse("30/03/1998", formatter), "12345678912", ""));
    }

    public PessoaEntity create(PessoaEntity pessoaEntity) {
        pessoaEntity.setIdPessoa(COUNTER.incrementAndGet());
        listaPessoaEntities.add(pessoaEntity);
        return pessoaEntity;
    }

    public List<PessoaEntity> list() {
        return listaPessoaEntities;
    }

    public PessoaEntity update(Integer id,
                               PessoaEntity pessoaEntityAtualizar) throws Exception {
        PessoaEntity pessoaEntityRecuperada = listaPessoaEntities.stream()
                .filter(pessoaEntity -> pessoaEntity.getIdPessoa().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não econtrada"));
        pessoaEntityRecuperada.setCpf(pessoaEntityAtualizar.getCpf());
        pessoaEntityRecuperada.setNome(pessoaEntityAtualizar.getNome());
        pessoaEntityRecuperada.setDataNascimento(pessoaEntityAtualizar.getDataNascimento());
        pessoaEntityRecuperada.setEmail(pessoaEntityAtualizar.getEmail());
        return pessoaEntityRecuperada;
    }

    public void delete(Integer id) throws Exception {
        PessoaEntity pessoaEntityRecuperada = listaPessoaEntities.stream()
                .filter(pessoaEntity -> pessoaEntity.getIdPessoa().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não econtrada"));
        listaPessoaEntities.remove(pessoaEntityRecuperada);
    }

    public List<PessoaEntity> listByName(String nome) {
        return listaPessoaEntities.stream()
                .filter(pessoaEntity -> pessoaEntity.getNome().toUpperCase().contains(nome.toUpperCase()))
                .collect(Collectors.toList());
    }
    public PessoaEntity getIdById(Integer id) throws RegraDeNegocioException {
        PessoaEntity pessoaEntityRecuperada = listaPessoaEntities.stream()
                .filter(pessoaEntity -> pessoaEntity.getIdPessoa().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não econtrada"));
        return pessoaEntityRecuperada;
    }
}

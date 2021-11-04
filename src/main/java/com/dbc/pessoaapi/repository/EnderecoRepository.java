package com.dbc.pessoaapi.repository;


import com.dbc.pessoaapi.entity.EnderecoEntity;
import com.dbc.pessoaapi.entity.TipoEndereco;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class EnderecoRepository {
    private static List<EnderecoEntity> listaEnderecoEntities = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();
    private AtomicInteger COUNTER2 = new AtomicInteger();

    public EnderecoRepository() {
        listaEnderecoEntities.add(new EnderecoEntity(COUNTER.incrementAndGet(), COUNTER2.incrementAndGet(), TipoEndereco.RESIDENCIAL,"Rua Amazonas ",15 ,"1º andar","417205140","Salvador","BA", "Brasil"));
        listaEnderecoEntities.add(new EnderecoEntity(COUNTER.incrementAndGet(), COUNTER2.incrementAndGet(), TipoEndereco.RESIDENCIAL,"Rua do sossego",11 ,"fundo","41205020","Salvador","BA", "Brasil"));
        listaEnderecoEntities.add(new EnderecoEntity(COUNTER.incrementAndGet(), COUNTER2.incrementAndGet(), TipoEndereco.RESIDENCIAL,"Rua julio cesar",17 ,"terreo","41705240","Salvador","BA", "Brasil"));
    }

    public List<EnderecoEntity> list() {
        return listaEnderecoEntities;
    }

    public List<EnderecoEntity> listByIdEndereco(Integer idEndereco) {
        return listaEnderecoEntities.stream()
                .filter(enderecoEntity -> enderecoEntity.getIdEndereco().equals(idEndereco))
                .collect(Collectors.toList());
    }

    public List<EnderecoEntity> listByIdPessoa(Integer idPessoa) {
        return listaEnderecoEntities.stream()
                .filter(enderecoEntity -> enderecoEntity.getIdPessoa().equals(idPessoa))
                .collect(Collectors.toList());
    }

    public EnderecoEntity create(EnderecoEntity enderecoEntity) {
        enderecoEntity.setIdEndereco(COUNTER.incrementAndGet());
        listaEnderecoEntities.add(enderecoEntity);
        return enderecoEntity;
    }
    public EnderecoEntity update(Integer idEndereco, EnderecoEntity enderecoEntityAtual) throws Exception {
        EnderecoEntity enderecoEntitySearch = listaEnderecoEntities.stream()
                .filter(enderecoEntity -> enderecoEntity.getIdEndereco().equals(idEndereco))
                .findFirst()
                .orElseThrow(() -> new Exception("Endereço não  foi encontrado"));
        enderecoEntitySearch.setIdPessoa(enderecoEntityAtual.getIdPessoa());
        enderecoEntitySearch.setTipo(enderecoEntityAtual.getTipo());
        enderecoEntitySearch.setLogradouro(enderecoEntityAtual.getLogradouro());
        enderecoEntitySearch.setNumero(enderecoEntityAtual.getNumero());
        enderecoEntitySearch.setComplemento(enderecoEntityAtual.getComplemento());
        enderecoEntitySearch.setCep(enderecoEntityAtual.getCep());
        enderecoEntitySearch.setCidade(enderecoEntityAtual.getCidade());
        enderecoEntitySearch.setEstado(enderecoEntityAtual.getEstado());
        enderecoEntitySearch.setPais(enderecoEntityAtual.getPais());
        return enderecoEntitySearch;
    }

    public void delete(Integer idEndereco) throws Exception {
        EnderecoEntity enderecobackup = listaEnderecoEntities.stream()
                .filter(enderecoEntity -> enderecoEntity.getIdEndereco().equals(idEndereco))
                .findFirst()
                .orElseThrow(() -> new Exception("Endereço não localizado"));
        listaEnderecoEntities.remove(enderecobackup);
    }


}
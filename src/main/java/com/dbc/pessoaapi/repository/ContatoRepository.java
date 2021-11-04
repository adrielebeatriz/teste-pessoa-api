package com.dbc.pessoaapi.repository;



import com.dbc.pessoaapi.entity.ContatoEntity;
import com.dbc.pessoaapi.entity.TipoContato;
import com.dbc.pessoaapi.exceptions.RegraDeNegocioException;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
@Repository
public class ContatoRepository {

    private AtomicInteger COUNTER = new AtomicInteger();
    private static List<ContatoEntity> listaContatoEntities = new ArrayList<>();
    private AtomicInteger COUNTER2 = new AtomicInteger();

    public ContatoRepository() {

        listaContatoEntities.add(new ContatoEntity(COUNTER.incrementAndGet(), COUNTER2.incrementAndGet(), TipoContato.COMERCIAL, "71987130989", "trabalho"));
        listaContatoEntities.add(new ContatoEntity(COUNTER.incrementAndGet(), COUNTER2.incrementAndGet(), TipoContato.RESIDENCIAL, "7182759675", "casa"));
        listaContatoEntities.add(new ContatoEntity(COUNTER.incrementAndGet(), COUNTER2.incrementAndGet(), TipoContato.COMERCIAL, "71986759796", "trabalho dois"));
    }

    public ContatoEntity create( Integer id, ContatoEntity contatoEntity) {

        contatoEntity.setIdContato(id);
        contatoEntity.setIdContato(COUNTER.incrementAndGet());
        listaContatoEntities.add(contatoEntity);

        return contatoEntity;
    }


    public List<ContatoEntity> list() {
        return listaContatoEntities;
    }

    public List<ContatoEntity> listByIdPessoa(Integer id) {
        return listaContatoEntities.stream()
                .filter(contatoEntity -> contatoEntity.getIdPessoa().equals(id))
                .collect(Collectors.toList());
    }

    public ContatoEntity update(Integer id, ContatoEntity contatoEntityAtualizar) throws Exception {
        ContatoEntity contatobackup = listaContatoEntities.stream()
                .filter(contatoEntity -> contatoEntity.getIdContato().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Contato não  foi encontrado"));
        contatobackup.setIdPessoa(contatoEntityAtualizar.getIdPessoa());
        contatobackup.setTipoContato(contatoEntityAtualizar.getTipoContato());
        contatobackup.setNumero(contatoEntityAtualizar.getNumero());
        contatobackup.setDescricao(contatoEntityAtualizar.getDescricao());
        return contatobackup;
    }

    public void delete(Integer id) throws Exception {
        ContatoEntity contatobackup = listaContatoEntities.stream()
                .filter(contatoEntity -> contatoEntity.getIdContato().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Contato não encontrado"));
        listaContatoEntities.remove(contatobackup);
    }
}

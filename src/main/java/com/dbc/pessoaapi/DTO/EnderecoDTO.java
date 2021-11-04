package com.dbc.pessoaapi.DTO;

import com.dbc.pessoaapi.entity.TipoEndereco;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class EnderecoDTO {
    private Integer idEndereco;
    private Integer idPessoa;
    @NotNull
    private TipoEndereco tipo;
    @NotEmpty
    @Size(max = 250)
    private String logradouro;
    @NotNull
    private Integer numero;
    private String complemento;
    @NotEmpty
    @NotNull
    @Size(max = 8,min = 8,message = "mais de 8 caracteres")
    private String cep;
    @NotEmpty
    @NotNull
    @Size(max = 250)
    private String cidade;
    @NotEmpty
    @NotNull
    private String estado;
    @NotEmpty
    @NotNull
    private String pais;
}
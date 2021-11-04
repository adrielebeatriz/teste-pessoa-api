package com.dbc.pessoaapi.DTO;


import com.dbc.pessoaapi.entity.TipoEndereco;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class EnderecoCreateDTO {
    private Integer idPessoa;
    @NotNull
    private TipoEndereco tipo;
    @NotEmpty
    @Size(max = 250)
    @ApiModelProperty(value  = "logradouro")
    private String logradouro;
    @NotNull
    @ApiModelProperty(value  = "numero da casa")
    private Integer numero;
    @ApiModelProperty(value  = "complemento")
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
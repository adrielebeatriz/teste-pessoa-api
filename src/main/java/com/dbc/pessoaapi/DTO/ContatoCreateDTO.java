package com.dbc.pessoaapi.DTO;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ContatoCreateDTO {
    private Integer idPessoa;

    @NotNull
    @Max(1)
    @Min(0)
    private Integer tipoContato;

    @NotNull
    @NotEmpty
    @Size(max = 13, message = "Não pode possuir mais que 13 caracteres")
    @ApiModelProperty(value  = "numero para contato")
    private String numero;

    @NotNull
    @NotEmpty
    private String descricao;
}
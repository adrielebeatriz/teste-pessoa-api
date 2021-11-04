package com.dbc.pessoaapi.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class PessoaCreateDTO {
    @NotEmpty
    @NotBlank
    @ToString.Exclude
    @ApiModelProperty(value  = "Nome da pessoa")
    private String nome;

    @NotNull
    @ApiModelProperty(value  = "Data de nascimento")
    private LocalDate dataNascimento;

    @Size(max = 11, min = 11, message = "cpf deve conter 11 caracteres")
    @NotNull
    @ApiModelProperty(value  = "cpf da pessoa")
    private String cpf;
    @ApiModelProperty(value  = "Email da pessoa")
    private String email;
}

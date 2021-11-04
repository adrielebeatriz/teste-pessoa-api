package com.dbc.pessoaapi.DTO;



import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class PessoaDTO {
    private Integer idPessoa;
    @NotEmpty
    @NotBlank
    @ToString.Exclude
    private String nome;
    private String email;

    @NotNull
    private LocalDate dataNascimento;
}
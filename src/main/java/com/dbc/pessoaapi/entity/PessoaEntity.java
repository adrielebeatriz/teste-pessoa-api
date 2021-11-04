package com.dbc.pessoaapi.entity;

import com.dbc.pessoaapi.DTO.PessoaCreateDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data // @Getter @Setter @ToString @EqualsAndHashCode @RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class PessoaEntity extends @Valid PessoaCreateDTO {
    private Integer idPessoa;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private String  email;
}
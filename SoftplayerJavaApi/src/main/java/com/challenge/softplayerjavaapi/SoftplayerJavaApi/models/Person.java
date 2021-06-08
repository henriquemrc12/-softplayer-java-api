package com.challenge.softplayerjavaapi.SoftplayerJavaApi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "person")
public class Person extends BaseModel implements Serializable {

    @NotBlank(message = "O nome é obrigatório!")
    private String name;

    private String gender;

    @Size(min = 11, max = 11, message = "O CPF deve conter 11 caracteres!")
    @NotBlank(message = "O CPF é obrigatório!")
    @Column(unique = true)
    private String cpf;

    @Email(message = "E-mail inválido!")
    private String email;

    @NotBlank(message = "O local de nascimento é obrigatório!")
    private String placeOfBirth;

    @NotNull(message = "A data de nascimento é obrigatória!")
    @Column(name = "birth_date")
    private LocalDate birthDate;

}

package br.com.api.models;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank()
    @Length(min = 5, max = 100)
    @NotNull(message = "O campo precisa ser preenchido.")
    @Column(length = 100, nullable = false)
    private String nome;

    @NotNull()
    @Length(max = 10)
    @Pattern(regexp = "Back-End|Front-End")
    @Column(length = 10, nullable = false)
    private String categoria;

}

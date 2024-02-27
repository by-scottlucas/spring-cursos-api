package br.com.api.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.validator.constraints.Length;

import br.com.api.enums.Categoria;
import br.com.api.enums.Status;
import br.com.api.enums.converters.CategoriaConverter;
import br.com.api.enums.converters.StatusConverter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_curso")
@SQLDelete(sql = "UPDATE tb_curso SET status = 'Inativo' WHERE id = ?")
@SQLRestriction("status = 'Ativo'")
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
    @Column(length = 10, nullable = false)
    @Convert(converter = CategoriaConverter.class)
    private Categoria categoria;

    @NotNull()
    @Column(length = 10, nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ATIVO;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Aula> aulas = new ArrayList<>();

}

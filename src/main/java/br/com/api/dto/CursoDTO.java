package br.com.api.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import br.com.api.enums.Categoria;
import br.com.api.enums.validation.ValueOfEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CursoDTO(
                Long id,
                @NotBlank @NotNull @Length(min = 5, max = 100) String nome,
                @NotNull @Length(max = 10) @ValueOfEnum(enumClass = Categoria.class) String categoria,
                @NotNull @NotEmpty @Valid List<AulaDTO> aulas) {
}

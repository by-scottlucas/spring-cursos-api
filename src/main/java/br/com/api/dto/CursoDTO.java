package br.com.api.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CursoDTO(
        Long id,
        @NotBlank @NotNull @Length(min = 5, max = 100) String nome,
        @NotNull @Length(max = 10) @Pattern(regexp = "Back-End|Front-End") String categoria) {
}

package br.com.api.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AulaDTO(
        Long id,
        @NotNull @NotBlank @Length(min = 5, max = 100) String nome,
        @NotNull @NotBlank @Length(min = 5, max = 11) String url) {

}

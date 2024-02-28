package br.com.api.dto;

import java.util.List;

public record CursoPageDTO(
        List<CursoDTO> cursos,
        long totalElementos,
        int totalPaginas) {
}

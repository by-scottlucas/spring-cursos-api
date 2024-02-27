package br.com.api.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.api.dto.AulaDTO;
import br.com.api.dto.CursoDTO;
import br.com.api.enums.Categoria;
import br.com.api.model.Aula;
import br.com.api.model.Curso;

@Component
public class CursoMapper {

    public CursoDTO toDTO(Curso curso) {
        if (curso == null) {
            return null;
        }

        List<AulaDTO> aulas = curso.getAulas()
                .stream()
                .map(aula -> new AulaDTO(aula.getId(), aula.getNome(), aula.getUrl()))
                .collect(Collectors.toList());

        return new CursoDTO(
                curso.getId(),
                curso.getNome(),
                curso.getCategoria().getValue(),
                aulas);
    }

    public Curso toEntity(CursoDTO cursoDTO) {

        if (cursoDTO == null) {
            return null;
        }

        Curso curso = new Curso();
        if (cursoDTO.id() != null) {
            curso.setId(cursoDTO.id());
        }
        curso.setNome(cursoDTO.nome());
        curso.setCategoria(convertCategoria(cursoDTO.categoria()));

        List<Aula> aulas = cursoDTO.aulas().stream().map(aulaDTO -> {
            var aula = new Aula();
            aula.setId(aulaDTO.id());
            aula.setNome(aulaDTO.nome());
            aula.setUrl(aulaDTO.url());
            return aula;
        }).collect(Collectors.toList());
        curso.setAulas(aulas);

        return curso;
    }

    public Categoria convertCategoria(String value) {
        if (value == null) {
            return null;
        }

        return switch (value) {
            case "Front-End" -> Categoria.FRONTEND;
            case "Back-End" -> Categoria.BACKEND;
            default -> throw new IllegalArgumentException("Categoria inválida: " + value);
        };

    }

}

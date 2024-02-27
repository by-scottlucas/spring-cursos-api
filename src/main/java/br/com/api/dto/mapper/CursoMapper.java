package br.com.api.dto.mapper;

import org.springframework.stereotype.Component;

import br.com.api.dto.CursoDTO;
import br.com.api.model.Curso;

@Component
public class CursoMapper {

    public CursoDTO toDTO(Curso curso) {
        if (curso == null) {
            return null;
        }

        return new CursoDTO(curso.getId(), curso.getNome(), curso.getCategoria());
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
        curso.setCategoria(cursoDTO.categoria());
        curso.setStatus("Ativo");
        return curso;
    }

}

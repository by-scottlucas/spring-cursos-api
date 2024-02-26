package br.com.api.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.api.exceptions.NotFoundException;
import br.com.api.models.Curso;
import br.com.api.repositorys.CursoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CursoService {

    private CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> list() {
        return cursoRepository.findAll();
    }

    public Curso create(@Valid Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso read(@PathVariable @NotNull @Positive Long id) {
        return cursoRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Curso update(@NotNull @Positive Long id, @Valid Curso curso) {
        return cursoRepository.findById(id)
                .map(response -> {
                    response.setNome(curso.getNome());
                    response.setCategoria(curso.getCategoria());
                    return cursoRepository.save(response);
                }).orElseThrow(() -> new NotFoundException(id));
    }

    public void delete(@PathVariable @NotNull @Positive Long id) {
        cursoRepository.delete(cursoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id)));
    }
}

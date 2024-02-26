package br.com.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.models.Curso;
import br.com.api.services.CursoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping()
    public List<Curso> list() {
        return cursoService.list();
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public Curso create(@RequestBody @Valid Curso curso) {
        return cursoService.create(curso);
    }

    @GetMapping("/{id}")
    public Curso read(@PathVariable @NotNull @Positive Long id) {
        return cursoService.read(id);
    }

    @PutMapping("/{id}")
    public Curso update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid Curso curso) {
        return cursoService.update(id, curso);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
        cursoService.delete(id);
    }

}
package br.com.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.models.Curso;
import br.com.api.repositorys.CursoRepository;

@RestController
@RequestMapping("/api/v1/cursos")
@CrossOrigin("http://localhost:4200")
public class CursosController {

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping()
    public @ResponseBody List<Curso> list() {
        return cursoRepository.findAll();
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public Curso create(@RequestBody Curso curso) {
        return cursoRepository.save(curso);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> read(@PathVariable Long id) {
        return cursoRepository.findById(id)
                .map(response -> ResponseEntity.ok().body(response))
                .orElse(ResponseEntity.notFound().build());
    }

}
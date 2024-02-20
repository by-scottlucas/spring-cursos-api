package com.lucas.crudspring.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.model.Curso;
import com.lucas.repository.CursoRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/cursos")
@AllArgsConstructor
public class CursosController {

    private final CursoRepository cursoRepository;

    @GetMapping
    public List<Curso> list() {
        return cursoRepository.findAll();
    }

}
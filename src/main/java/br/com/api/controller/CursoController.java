package br.com.api.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.dto.CursoDTO;
import br.com.api.dto.CursoPageDTO;
import br.com.api.service.CursoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Validated
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping()
    public CursoPageDTO list(
            @RequestParam(defaultValue = "0") @PositiveOrZero int Page,
            @RequestParam(defaultValue = "10") @Positive @Max(100) int pageSize) {
        return cursoService.list(pageSize, pageSize);
    }
    // @GetMapping()
    // public List<CursoDTO> list() {
    // return cursoService.list();
    // }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public CursoDTO create(@RequestBody @Valid CursoDTO curso) {
        return cursoService.create(curso);
    }

    @GetMapping("/{id}")
    public CursoDTO read(@PathVariable @NotNull @Positive Long id) {
        return cursoService.read(id);
    }

    @PutMapping("/{id}")
    public CursoDTO update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid @NotNull CursoDTO curso) {
        return cursoService.update(id, curso);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
        cursoService.delete(id);
    }

}
package br.com.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.api.dto.CursoDTO;
import br.com.api.dto.CursoPageDTO;
import br.com.api.dto.mapper.CursoMapper;
import br.com.api.exception.NotFoundException;
import br.com.api.model.Curso;
import br.com.api.repository.CursoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Validated
@Service
public class CursoService {

    private CursoRepository cursoRepository;
    private CursoMapper cursoMapper;

    public CursoService(CursoRepository cursoRepository, CursoMapper cursoMapper) {
        this.cursoRepository = cursoRepository;
        this.cursoMapper = cursoMapper;
    }

    public CursoPageDTO list(@PositiveOrZero int pageIndex, @Positive @Max(100) int pageSize) {
        Page<Curso> cursoPage = cursoRepository.findAll(PageRequest.of(pageIndex, pageSize));
        List<CursoDTO> cursos = cursoPage.get().map(cursoMapper::toDTO).collect(Collectors.toList());
        return new CursoPageDTO(cursos, cursoPage.getTotalElements(), cursoPage.getTotalPages());
    }

    public CursoDTO create(@Valid CursoDTO curso) {
        return cursoMapper.toDTO(cursoRepository.save(cursoMapper.toEntity(curso)));
    }

    public CursoDTO read(@NotNull @Positive Long id) {
        return cursoRepository.findById(id)
                .map(cursoMapper::toDTO)
                .orElseThrow(() -> new NotFoundException(id));
    }

    public CursoDTO update(@NotNull @Positive Long id, @Valid @NotNull CursoDTO cursoDTO) {
        return cursoRepository.findById(id)
                .map(response -> {
                    Curso curso = cursoMapper.toEntity(cursoDTO);
                    response.setNome(cursoDTO.nome());
                    response.setCategoria(cursoMapper.convertCategoria(cursoDTO.categoria()));
                    // response.setAulas(curso.getAulas());
                    response.getAulas().clear();
                    curso.getAulas().forEach(response.getAulas()::add);
                    return cursoMapper.toDTO(cursoRepository.save(response));
                }).orElseThrow(() -> new NotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {
        cursoRepository.delete(cursoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id)));
    }
}

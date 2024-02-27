package br.com.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.api.dto.CursoDTO;
import br.com.api.dto.mapper.CursoMapper;
import br.com.api.exception.NotFoundException;
import br.com.api.repository.CursoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CursoService {

    private CursoRepository cursoRepository;
    private CursoMapper cursoMapper;

    public CursoService(CursoRepository cursoRepository, CursoMapper cursoMapper) {
        this.cursoRepository = cursoRepository;
        this.cursoMapper = cursoMapper;
    }

    public List<CursoDTO> list() {
        return cursoRepository.findAll()
                .stream()
                .map(cursoMapper::toDTO)
                .collect(Collectors.toList());
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
                    response.setNome(cursoDTO.nome());
                    response.setCategoria(cursoMapper.convertCategoria(cursoDTO.categoria()));
                    return cursoMapper.toDTO(cursoRepository.save(response));
                }).orElseThrow(() -> new NotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {
        cursoRepository.delete(cursoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id)));
    }
}

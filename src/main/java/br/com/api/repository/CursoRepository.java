package br.com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {}

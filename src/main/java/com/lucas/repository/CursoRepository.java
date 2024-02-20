package com.lucas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucas.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {}

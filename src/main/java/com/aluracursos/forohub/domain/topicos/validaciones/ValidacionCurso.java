package com.aluracursos.forohub.domain.topicos.validaciones;

import com.aluracursos.forohub.domain.cursos.CursoRepository;
import com.aluracursos.forohub.domain.topicos.DatosRegistroTopico;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacionCurso {
    @Autowired
    private CursoRepository repository;

    public void validar(DatosRegistroTopico datosTopico) {
        if (!repository.findById(datosTopico.id_curso()).isPresent()) {
            throw new ValidationException("Curso no encontrado");
        }
    }
}
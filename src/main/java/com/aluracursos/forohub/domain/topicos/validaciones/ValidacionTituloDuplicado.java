package com.aluracursos.forohub.domain.topicos.validaciones;

import com.aluracursos.forohub.domain.topicos.DatosRegistroTopico;
import com.aluracursos.forohub.domain.topicos.TopicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacionTituloDuplicado {
    @Autowired
    private TopicoRepository repository;

    public void validar(DatosRegistroTopico datosTopico) {
        if (repository.findByTitulo(datosTopico.titulo()).isPresent()) {
            throw new ValidationException("Ya se registró un tópico con ese título");
        }
    }
}
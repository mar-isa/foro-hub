package com.aluracursos.forohub.domain.cursos;

import com.aluracursos.forohub.domain.usuarios.DatosRespuestaUsuario;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public DatosRespuestaCurso registrarCurso(DatosRegistroCurso datosRegistro) {
        Curso curso = new Curso(datosRegistro);
        Curso cursoAgregado = cursoRepository.save(curso);
        return new DatosRespuestaCurso(cursoAgregado);
    }

    public Page listadoUsuario(Pageable paginacion) {
        return cursoRepository.findAll(paginacion).map(DatosRespuestaCurso::new);
    }

    public DatosRespuestaCurso actualizarUsuario(DatosActualizarCurso datosActualizar) {
        if (cursoRepository.findById(datosActualizar.id()).isEmpty()) { throw new ValidationException("Curso no encontrado"); }
        Curso curso = cursoRepository.getReferenceById(datosActualizar.id());
        curso.actualizarDatos(datosActualizar);
        return new DatosRespuestaCurso(curso);
    }

    public void eliminarCurso(Long id) {
        if (cursoRepository.findById(id).isEmpty()) { throw new ValidationException("Curso no encontrado"); }
        cursoRepository.delete(cursoRepository.getReferenceById(id));
    }
}
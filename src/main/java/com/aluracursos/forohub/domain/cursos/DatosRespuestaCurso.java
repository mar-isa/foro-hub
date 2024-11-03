package com.aluracursos.forohub.domain.cursos;

public record DatosRespuestaCurso(Long id,
                                  String nombre,
                                  String categoria) {

    public DatosRespuestaCurso(Curso curso) {
        this(curso.getId(), curso.getNombre(), curso.getCategoria().toString());
    }
}
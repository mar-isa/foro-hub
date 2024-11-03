package com.aluracursos.forohub.domain.topicos;

import java.time.LocalDateTime;

public record DatosDetallesTopico(Long id,
                                  String titulo,
                                  String mensaje,
                                  LocalDateTime fechaCreacion,
                                  String status,
                                  String usuario,
                                  String curso) {

    public DatosDetallesTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus().toString(),
                topico.getUsuario().getNombre(), topico.getCurso().getNombre());
    }
}

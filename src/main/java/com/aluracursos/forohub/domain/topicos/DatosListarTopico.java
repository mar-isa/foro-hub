package com.aluracursos.forohub.domain.topicos;

import java.time.LocalDateTime;

public record DatosListarTopico(String titulo,
                                String mensaje,
                                LocalDateTime fechaCreacion,
                                String status,
                                Long id_usuario,
                                Long id_curso) {

    public DatosListarTopico(Topico topico) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus().toString(), topico.getUsuario().getId(), topico.getCurso().getId());
    }
}

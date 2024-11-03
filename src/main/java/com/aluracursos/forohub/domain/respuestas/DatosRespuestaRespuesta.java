package com.aluracursos.forohub.domain.respuestas;

import java.time.LocalDateTime;

public record DatosRespuestaRespuesta(Long id,
                                      String mensaje,
                                      Long id_topico,
                                      Long id_usuario) {

    public DatosRespuestaRespuesta(Respuesta respuesta) {
        this(respuesta.getId(), respuesta.getMensaje(), respuesta.getTopico().getId(), respuesta.getUsuario().getId());
    }
}
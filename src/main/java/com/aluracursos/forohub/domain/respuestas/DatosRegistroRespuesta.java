package com.aluracursos.forohub.domain.respuestas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroRespuesta(@NotBlank String mensaje,
                                     @NotNull Long id_topico,
                                     @NotNull Long id_usuario) {
}

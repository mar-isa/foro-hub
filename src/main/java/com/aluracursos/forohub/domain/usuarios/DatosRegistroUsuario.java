package com.aluracursos.forohub.domain.usuarios;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroUsuario(@NotBlank String nombre,
                                   @NotBlank String email,
                                   @NotBlank String clave,
                                   @NotNull Rol rol) {
}
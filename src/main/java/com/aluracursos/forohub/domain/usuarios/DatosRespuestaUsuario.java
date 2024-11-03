package com.aluracursos.forohub.domain.usuarios;

public record DatosRespuestaUsuario(Long id,
                                    String nombre,
                                    String email,
                                    String rol) {

    public DatosRespuestaUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getRol().toString());
    }
}

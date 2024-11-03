package com.aluracursos.forohub.domain.usuarios;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public DatosRespuestaUsuario registrarUsuario(DatosRegistroUsuario datosRegistro) {
        Usuario usuario = new Usuario(datosRegistro);
        Usuario usuarioAgregado = usuarioRepository.save(usuario);
        return new DatosRespuestaUsuario(usuarioAgregado);
    }

    public Page listadoUsuario(Pageable paginacion) {
        return usuarioRepository.findAll(paginacion).map(DatosRespuestaUsuario::new);
    }

    public DatosRespuestaUsuario actualizarUsuario(DatosActualizarUsuario datosActualizar) {
        if (usuarioRepository.findById(datosActualizar.id()).isEmpty()) { throw new ValidationException("Usuario no encontrado"); }
        Usuario usuario = usuarioRepository.getReferenceById(datosActualizar.id());
        usuario.actualizarDatos(datosActualizar);
        return new DatosRespuestaUsuario(usuario);
    }

    public void eliminarUsuario(Long id) {
        if (usuarioRepository.findById(id).isEmpty()) { throw new ValidationException("Usuario no encontrado"); }
        usuarioRepository.delete(usuarioRepository.getReferenceById(id));
    }
}
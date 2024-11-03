package com.aluracursos.forohub.domain.topicos.validaciones;

import com.aluracursos.forohub.domain.topicos.DatosRegistroTopico;
import com.aluracursos.forohub.domain.usuarios.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacionUsuario implements ValidadorDeTopicos{
    @Autowired
    private UsuarioRepository repository;

    public void validar(DatosRegistroTopico datosTopico) {
        if (!repository.findById(datosTopico.id_usuario()).isPresent()) {
            throw new ValidationException("Usuario no encontrado");
        }
    }
}
package com.aluracursos.forohub.domain.respuestas;

import com.aluracursos.forohub.domain.topicos.TopicoRepository;
import com.aluracursos.forohub.domain.usuarios.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RespuestaService {
    @Autowired
    private RespuestaRepository respuestaRepository;
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public DatosRespuestaRespuesta registrarRespuesta(DatosRegistroRespuesta datosRegistro) {
        var topico = topicoRepository.findById(datosRegistro.id_topico())
                .orElseThrow(() -> new ValidationException("Tópico no encontrado"));
        var usuario = usuarioRepository.findById(datosRegistro.id_usuario())
                .orElseThrow(() -> new ValidationException("Usuario no encontrado"));

        Respuesta respuesta = new Respuesta(datosRegistro, topico, usuario);
        Respuesta respuestaAgregada = respuestaRepository.save(respuesta);
        return new DatosRespuestaRespuesta(respuestaAgregada);
    }

    public Page listadoResppuestas(Pageable paginacion) {
        return respuestaRepository.findAll(paginacion).map(DatosRespuestaRespuesta::new);
    }

    public DatosRespuestaRespuesta actualizarRespuesta(DatosActualizarRespuesta datosActualizar) {
        if (respuestaRepository.findById(datosActualizar.id()).isEmpty()) { throw new ValidationException("Respuesta no encontrado"); }
        Respuesta respuesta = respuestaRepository.getReferenceById(datosActualizar.id());
        respuesta.actualizarDatos(datosActualizar);
        return new DatosRespuestaRespuesta(respuesta);
    }

    public void eliminarRespuesta(Long id) {
        if (respuestaRepository.findById(id).isEmpty()) { throw new ValidationException("Respuesta no encontrado"); }
        respuestaRepository.deleteById(id);
    }
}
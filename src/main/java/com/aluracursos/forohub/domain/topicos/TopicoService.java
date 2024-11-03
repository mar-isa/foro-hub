package com.aluracursos.forohub.domain.topicos;

import com.aluracursos.forohub.domain.cursos.CursoRepository;
import com.aluracursos.forohub.domain.topicos.validaciones.ValidadorDeTopicos;
import com.aluracursos.forohub.domain.usuarios.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Service
public class TopicoService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private List<ValidadorDeTopicos> validaciones;

    public DatosRespuestaTopico registrarTopico(DatosRegistroTopico datosRegistro) {
        var usuario = usuarioRepository.findById(datosRegistro.id_usuario())
                .orElseThrow(() -> new ValidationException("Usuario no encontrado"));
        var curso = cursoRepository.findById(datosRegistro.id_curso())
                .orElseThrow(() -> new ValidationException("Curso no encontrado"));
        validaciones.forEach(v -> v.validar(datosRegistro));

        Topico topico = new Topico(datosRegistro, usuario, curso);
        Topico topicoAgregado = topicoRepository.save(topico);
        return new DatosRespuestaTopico(topicoAgregado);
    }

    public Page<DatosRespuestaTopico> listarTopicos(Pageable paginacion) {
        return topicoRepository.buscarPorFecha(paginacion).map(DatosRespuestaTopico::new);
    }

    public DatosDetallesTopico detallesTopico(Long id) {
        if (topicoRepository.findById(id).isPresent()) {
            var topico = topicoRepository.getReferenceById(id);
            return new DatosDetallesTopico(topico); }
        else { throw new ValidationException("Tópico no encontrado. Verifique que el id sea correcto."); }
    }

    public DatosRespuestaTopico actualizarTopico(DatosActualizarTopico datosActualizar) {
        if (topicoRepository.findById(datosActualizar.id()).isEmpty()) { throw new ValidationException("Tópico no encontrado"); }
            Topico topico = topicoRepository.getReferenceById(datosActualizar.id());
            topico.actualizarDatos(datosActualizar);
            return new DatosRespuestaTopico(topico);
    }

    public void eliminarTopico(Long id) {
        if (topicoRepository.findById(id).isEmpty()) { throw new ValidationException("Tópico no encontrado"); }
        topicoRepository.deleteById(id);
    }
}
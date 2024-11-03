package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.respuestas.DatosActualizarRespuesta;
import com.aluracursos.forohub.domain.respuestas.DatosRegistroRespuesta;
import com.aluracursos.forohub.domain.respuestas.DatosRespuestaRespuesta;
import com.aluracursos.forohub.domain.respuestas.RespuestaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {
    @Autowired
    private RespuestaService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaRespuesta> registrarRespuesta(@RequestBody @Valid DatosRegistroRespuesta datosRegistro,
                                                                      UriComponentsBuilder uriBuilder) {
        DatosRespuestaRespuesta datosRespuesta = service.registrarRespuesta(datosRegistro);
        URI url = uriBuilder.path("/respuestas/{id}").buildAndExpand(datosRespuesta.id()).toUri();
        return ResponseEntity.created(url).body(datosRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page> listarRespuestas(@PageableDefault(size = 5) Pageable paginacion) {
        return ResponseEntity.ok(service.listadoResppuestas(paginacion));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarRespuesta(@RequestBody @Valid DatosActualizarRespuesta datosActualizar) {
        DatosRespuestaRespuesta datosRespuesta = service.actualizarRespuesta(datosActualizar);
        return ResponseEntity.ok(datosRespuesta);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRespuesta(@PathVariable Long id) {
        service.eliminarRespuesta(id);
        return ResponseEntity.noContent().build();
    }
}
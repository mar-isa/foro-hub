package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.topicos.*;
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
@RequestMapping("topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {
    @Autowired
    private TopicoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistro,
                                                                UriComponentsBuilder uriBuilder) {
        DatosRespuestaTopico datosRespuesta = service.registrarTopico(datosRegistro);
        URI url = uriBuilder.path("/topicos/{id}").buildAndExpand(datosRespuesta.id()).toUri();
        return ResponseEntity.created(url).body(datosRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page> listarTopicos(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(service.listarTopicos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity mostrarTopico(@PathVariable Long id) {
        return ResponseEntity.ok(service.detallesTopico(id));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizar) {
        DatosRespuestaTopico datosRespuesta = service.actualizarTopico(datosActualizar);
        return ResponseEntity.ok(datosRespuesta);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        service.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }
}
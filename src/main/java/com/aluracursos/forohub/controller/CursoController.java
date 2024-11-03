package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.cursos.*;
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
@RequestMapping("cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {
    @Autowired
    private CursoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaCurso> registrarCurso(@RequestBody @Valid DatosRegistroCurso datosRegistro,
                                                              UriComponentsBuilder uriBuilder) {
        DatosRespuestaCurso datosRespuesta = service.registrarCurso(datosRegistro);
        URI url = uriBuilder.path("/cursos/{id}").buildAndExpand(datosRespuesta.id()).toUri();
        return ResponseEntity.created(url).body(datosRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page> listarCursos(@PageableDefault(size = 5) Pageable paginacion) {
        return ResponseEntity.ok(service.listadoUsuario(paginacion));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarCurso(@RequestBody @Valid DatosActualizarCurso datosActualizar) {
        DatosRespuestaCurso datosRespuesta = service.actualizarUsuario(datosActualizar);
        return ResponseEntity.ok(datosRespuesta);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarCurso(@PathVariable Long id) {
        service.eliminarCurso(id);
        return ResponseEntity.noContent().build();
    }
}
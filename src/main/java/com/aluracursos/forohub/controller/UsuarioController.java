package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.usuarios.DatosActualizarUsuario;
import com.aluracursos.forohub.domain.usuarios.DatosRegistroUsuario;
import com.aluracursos.forohub.domain.usuarios.DatosRespuestaUsuario;
import com.aluracursos.forohub.domain.usuarios.UsuarioService;
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
@RequestMapping("usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaUsuario> registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistro,
                                                                  UriComponentsBuilder uriBuilder) {
        DatosRespuestaUsuario datosRespuesta = service.registrarUsuario(datosRegistro);
        URI url = uriBuilder.path("/usuarios/{id}").buildAndExpand(datosRespuesta.id()).toUri();
        return ResponseEntity.created(url).body(datosRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page> listarUsuarios(@PageableDefault(size = 5) Pageable paginacion) {
        return ResponseEntity.ok(service.listadoUsuario(paginacion));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarUsuario(@RequestBody @Valid DatosActualizarUsuario datosActualizar) {
        DatosRespuestaUsuario datosRespuesta = service.actualizarUsuario(datosActualizar);
        return ResponseEntity.ok(datosRespuesta);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarUsuario(@PathVariable Long id) {
        service.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
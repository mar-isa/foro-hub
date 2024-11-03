package com.aluracursos.forohub.domain.topicos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Optional<Topico> findByTitulo(String titulo);
    Optional<Topico> findByMensaje(String mensaje);
    @Query("select t from Topico t order by t.fechaCreacion asc")
    Page<Topico> buscarPorFecha(Pageable paginacion);
}

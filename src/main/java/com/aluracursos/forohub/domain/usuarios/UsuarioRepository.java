package com.aluracursos.forohub.domain.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
//    Optional<Usuario> findById(Long id);
    UserDetails findByEmail(String username);
}
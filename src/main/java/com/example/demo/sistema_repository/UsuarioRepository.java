package com.example.demo.sistema_repository;

import java.util.Optional;

import com.example.demo.sistem_model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    
    public Optional<Usuario> findByEmail(String email);

    public Optional<Usuario> findByUsername(String username);

    public Optional<Usuario> findByUsernameOrEmail(String username,String email);

    public Boolean existsByUsername(String username);

    public Boolean existsByEmail(String email);
}

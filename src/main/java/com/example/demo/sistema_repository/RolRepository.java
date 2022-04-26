package com.example.demo.sistema_repository;

import java.util.Optional;

import com.example.demo.sistem_model.Rol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RolRepository extends JpaRepository<Rol,Long> {
    
    Optional<Rol> findByNombre(String nombre);
}

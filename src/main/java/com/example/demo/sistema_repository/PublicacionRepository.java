package com.example.demo.sistema_repository;

import com.example.demo.sistem_model.PublicacionEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicacionRepository extends JpaRepository<PublicacionEntity, Long> {
    
}

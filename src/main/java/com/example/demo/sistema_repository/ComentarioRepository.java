package com.example.demo.sistema_repository;

import java.util.List;

import com.example.demo.sistem_model.ComentarioEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<ComentarioEntity, Long> {

    List<ComentarioEntity> findBypublicacionId(Long publicacionId);

}

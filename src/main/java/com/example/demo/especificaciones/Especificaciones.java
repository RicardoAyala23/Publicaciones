package com.example.demo.especificaciones;

import java.util.List;

import com.example.demo.filtros.Filtro;
import com.example.demo.sistem_model.PublicacionEntity;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import static org.springframework.data.jpa.domain.Specification.where;

@Component
public class Especificaciones {

    public Specification<PublicacionEntity> obtenerEspecificacionesPorFiltros(List<Filtro> filtro) {

        Specification<PublicacionEntity> especificacion = where(creandoEspecificacion(filtro.remove(0)));

        for (Filtro entrada : filtro) {

            especificacion = especificacion.and(creandoEspecificacion(entrada));

        }

        return especificacion;
    }

    private Specification<PublicacionEntity> creandoEspecificacion(Filtro entrada) {

        switch (entrada.getOperador()) {

            case LIKE:
                return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(entrada.getCampo()),
                        entrada.getValor());
            default:
                throw new RuntimeException("Operation not supported yet");
        }

    }
}

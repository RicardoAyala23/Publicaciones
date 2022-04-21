package com.example.demo.sistema_service;

import java.util.List;

import com.example.demo.sistem_request.PublicacionRequest;
import com.example.demo.sistem_response.PublicacionResonse;
import com.example.demo.sistem_response.PublicacionResponseAtributos;
import com.example.demo.sistem_response.PublicacionResponseCreate;

public interface PublicacionService {

    public PublicacionResponseCreate crearPublicacion(PublicacionRequest publicacionRequest);

    public List<PublicacionResonse> obtenerTodasLasPublicaciones();

    public PublicacionResponseAtributos obtenerTodasLasPublicacionesConPaginacion(int page,int size,String columna,String sortDir);

    public PublicacionResonse obtenerPorId(Long id);

    public PublicacionResonse actualizarPublicacion(PublicacionRequest publicacionRequest,Long id);

    public boolean eliminarPublicacion(Long id);
    
}

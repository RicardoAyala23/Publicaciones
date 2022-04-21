package com.example.demo.sistema_service;

import java.util.List;

import com.example.demo.sistem_request.ComentarioRequest;
import com.example.demo.sistem_response.ComentarioResponse;
import com.example.demo.sistem_response.ComentarioResponseCreate;

public interface ComentarioServicio {
    
    public ComentarioResponseCreate crearComentario(Long publicacionId,ComentarioRequest comentarioRequest);

    public List<ComentarioResponse> obtenerComentariosPorPublicacionId(Long publicacionId);
}
package com.example.demo.sistema_service;

import java.util.List;

import com.example.demo.sistem_request.ComentarioRequest;
import com.example.demo.sistem_response.ComentarioResponse;
import com.example.demo.sistem_response.ComentarioResponseCreate;

public interface ComentarioServicio {
    
    public ComentarioResponseCreate crearComentario(Long publicacionId,ComentarioRequest comentarioRequest);

    public List<ComentarioResponse> obtenerComentariosPorPublicacionId(Long publicacionId);

    public ComentarioResponse obtenerComentarioPorId(Long publicacionId,Long comentarioId);

    public ComentarioResponse actualizarComentario(Long publicacionId,Long comentarioId,ComentarioRequest comentarioRequest);

    public Boolean borrarComentario(Long publicacionId,Long comentarioId);

}
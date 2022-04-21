package com.example.demo.sistema_service;

import java.util.List;
import java.util.Optional;

import com.example.demo.exceptions.ResouceNotFoundException;
import com.example.demo.sistem_mapper.ComentarioMapper;
import com.example.demo.sistem_model.ComentarioEntity;
import com.example.demo.sistem_model.PublicacionEntity;
import com.example.demo.sistem_request.ComentarioRequest;
import com.example.demo.sistem_response.ComentarioResponse;
import com.example.demo.sistem_response.ComentarioResponseCreate;
import com.example.demo.sistema_repository.ComentarioRepository;
import com.example.demo.sistema_repository.PublicacionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioServiceImpl implements ComentarioServicio {

    @Autowired
    private ComentarioMapper comentarioMapper;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Override
    public ComentarioResponseCreate crearComentario(Long publicacionId, ComentarioRequest comentarioRequest) {

        ComentarioEntity comentarioEntity = comentarioMapper.comentarioRequestToComentarioEntity(comentarioRequest);

        Optional<PublicacionEntity> publicacionOptional = publicacionRepository.findById(publicacionId);

        if (publicacionOptional.isPresent()) {

            comentarioEntity.setPublicacion(publicacionOptional.get());

            ComentarioEntity comentarioNuevo = comentarioRepository.save(comentarioEntity);

            ComentarioResponseCreate comentarioResponsecreate = new ComentarioResponseCreate();

            comentarioResponsecreate.setId(comentarioNuevo.getId());

            return comentarioResponsecreate;

        }else{

            throw new ResouceNotFoundException("", "Publicacion", "id", publicacionId);

        }

    }

    @Override
    public List<ComentarioResponse> obtenerComentariosPorPublicacionId(Long publicacionId) {
        List<ComentarioEntity> comentarioEntities = comentarioRepository.findBypublicacionId(publicacionId);

        List<ComentarioResponse> comentarioResponses = comentarioMapper.comentariosEntitiesToComentariosResponses(comentarioEntities);
        return comentarioResponses;
    }

}

package com.example.demo.sistema_service;

import java.util.List;
import java.util.Optional;

import com.example.demo.exceptions.BlogAppExceptions;
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
import org.springframework.http.HttpStatus;
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

        } else {

            throw new ResouceNotFoundException("", "Publicacion", "id", publicacionId);

        }

    }

    @Override
    public List<ComentarioResponse> obtenerComentariosPorPublicacionId(Long publicacionId) {
        List<ComentarioEntity> comentarioEntities = comentarioRepository.findBypublicacionId(publicacionId);

        List<ComentarioResponse> comentarioResponses = comentarioMapper
                .comentariosEntitiesToComentariosResponses(comentarioEntities);
        return comentarioResponses;
    }

    @Override
    public ComentarioResponse obtenerComentarioPorId(Long publicacionId, Long comentarioId) {
        Optional<PublicacionEntity> publicacionOptional = publicacionRepository.findById(publicacionId);

        if (publicacionOptional.isPresent()) {

            Optional<ComentarioEntity> comentarioOptional = comentarioRepository.findById(comentarioId);

            if (comentarioOptional.isPresent()) {

                if (!comentarioOptional.get().getPublicacion().getId().equals(publicacionOptional.get().getId())) {

                    throw new BlogAppExceptions(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");

                }

                ComentarioResponse comentarioResponse = comentarioMapper
                        .comentarioEntityToComentarioResponse(comentarioOptional.get());

                return comentarioResponse;

            } else {

                throw new ResouceNotFoundException("", "Comentario", "id", comentarioId);

            }

        } else {

            throw new ResouceNotFoundException("", "Publicacion", "id", publicacionId);

        }
    }

    @Override
    public ComentarioResponse actualizarComentario(Long publicacionId, Long comentarioId,
            ComentarioRequest comentarioRequest) {
        // Metodo de busqueda de la publicacion y el comentario por el Id
        // En este metodo vamos hacer lo mismo que en el metodo anterior pero mandando
        // la Exepcion por medio de una funcion lamba

        PublicacionEntity publicacionEntity = publicacionRepository.findById(publicacionId)
                .orElseThrow(() -> new ResouceNotFoundException("", "Publicacion", "id", publicacionId));

        ComentarioEntity comentarioEntity = comentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new ResouceNotFoundException("", "Publicacion", "id", comentarioId));

        if (!comentarioEntity.getPublicacion().getId().equals(publicacionEntity.getId())) {

            throw new BlogAppExceptions(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");

        }

        comentarioEntity.setNombre(comentarioRequest.getNombre());
        comentarioEntity.setEmail(comentarioRequest.getEmail());
        comentarioEntity.setCuerpo(comentarioRequest.getCuerpo());

        comentarioEntity = comentarioRepository.save(comentarioEntity);

        ComentarioResponse comentarioResponse = comentarioMapper.comentarioEntityToComentarioResponse(comentarioEntity);
        return comentarioResponse;
    }

    @Override
    public Boolean borrarComentario(Long publicacionId, Long comentarioId) {
        PublicacionEntity publicacionEntity = publicacionRepository.findById(publicacionId)
                .orElseThrow(() -> new ResouceNotFoundException("", "Publicacion", "id", publicacionId));

        ComentarioEntity comentarioEntity = comentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new ResouceNotFoundException("", "Publicacion", "id", comentarioId));

        if (!comentarioEntity.getPublicacion().getId().equals(publicacionEntity.getId())) {

            throw new BlogAppExceptions(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");

        }
        try {

            comentarioRepository.delete(comentarioEntity);

            return true;

        } catch (Exception ex) {

            return false;

        }
    }

}

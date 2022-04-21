package com.example.demo.sistem_mapper;

import java.util.List;

import com.example.demo.sistem_model.ComentarioEntity;
import com.example.demo.sistem_request.ComentarioRequest;
import com.example.demo.sistem_response.ComentarioResponse;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ComentarioMapper {
    //Aqui se hacen los mappers
    public abstract ComentarioEntity comentarioRequestToComentarioEntity(ComentarioRequest comentarioRequest);

    public abstract List<ComentarioResponse> comentariosEntitiesToComentariosResponses(List<ComentarioEntity> comentarioEntities);
    
}

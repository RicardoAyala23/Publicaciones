package com.example.demo.sistem_mapper;

import com.example.demo.sistem_model.ComentarioEntity;
import com.example.demo.sistem_request.ComentarioRequest;
import com.example.demo.sistem_response.ComentarioResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-29T10:26:39-0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.3 (Eclipse Adoptium)"
)
@Component
public class ComentarioMapperImpl implements ComentarioMapper {

    @Override
    public ComentarioEntity comentarioRequestToComentarioEntity(ComentarioRequest comentarioRequest) {
        if ( comentarioRequest == null ) {
            return null;
        }

        ComentarioEntity comentarioEntity = new ComentarioEntity();

        comentarioEntity.setNombre( comentarioRequest.getNombre() );
        comentarioEntity.setEmail( comentarioRequest.getEmail() );
        comentarioEntity.setCuerpo( comentarioRequest.getCuerpo() );

        return comentarioEntity;
    }

    @Override
    public List<ComentarioResponse> comentariosEntitiesToComentariosResponses(List<ComentarioEntity> comentarioEntities) {
        if ( comentarioEntities == null ) {
            return null;
        }

        List<ComentarioResponse> list = new ArrayList<ComentarioResponse>( comentarioEntities.size() );
        for ( ComentarioEntity comentarioEntity : comentarioEntities ) {
            list.add( comentarioEntityToComentarioResponse( comentarioEntity ) );
        }

        return list;
    }

    @Override
    public ComentarioResponse comentarioEntityToComentarioResponse(ComentarioEntity comentarioEntity) {
        if ( comentarioEntity == null ) {
            return null;
        }

        ComentarioResponse comentarioResponse = new ComentarioResponse();

        comentarioResponse.setId( comentarioEntity.getId() );
        comentarioResponse.setNombre( comentarioEntity.getNombre() );
        comentarioResponse.setEmail( comentarioEntity.getEmail() );
        comentarioResponse.setCuerpo( comentarioEntity.getCuerpo() );

        return comentarioResponse;
    }
}

package com.example.demo.sistem_mapper;

import com.example.demo.sistem_model.ComentarioEntity;
import com.example.demo.sistem_request.ComentarioRequest;
import com.example.demo.sistem_response.ComentarioResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-21T10:13:04-0500",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.100.v20220318-0906, environment: Java 17.0.2 (Eclipse Adoptium)"
)
@Component
public class ComentarioMapperImpl implements ComentarioMapper {

    @Override
    public ComentarioEntity comentarioRequestToComentarioEntity(ComentarioRequest comentarioRequest) {
        if ( comentarioRequest == null ) {
            return null;
        }

        ComentarioEntity comentarioEntity = new ComentarioEntity();

        comentarioEntity.setCuerpo( comentarioRequest.getCuerpo() );
        comentarioEntity.setEmail( comentarioRequest.getEmail() );
        comentarioEntity.setId( comentarioRequest.getId() );
        comentarioEntity.setNombre( comentarioRequest.getNombre() );

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

    protected ComentarioResponse comentarioEntityToComentarioResponse(ComentarioEntity comentarioEntity) {
        if ( comentarioEntity == null ) {
            return null;
        }

        ComentarioResponse comentarioResponse = new ComentarioResponse();

        comentarioResponse.setCuerpo( comentarioEntity.getCuerpo() );
        comentarioResponse.setEmail( comentarioEntity.getEmail() );
        comentarioResponse.setId( comentarioEntity.getId() );
        comentarioResponse.setNombre( comentarioEntity.getNombre() );

        return comentarioResponse;
    }
}

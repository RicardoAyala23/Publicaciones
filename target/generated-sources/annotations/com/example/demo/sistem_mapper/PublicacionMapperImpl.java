package com.example.demo.sistem_mapper;

import com.example.demo.sistem_model.PublicacionEntity;
import com.example.demo.sistem_request.PublicacionRequest;
import com.example.demo.sistem_response.PublicacionResonse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-29T10:26:39-0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.3 (Eclipse Adoptium)"
)
@Component
public class PublicacionMapperImpl implements PublicacionMapper {

    @Override
    public PublicacionEntity publicacionRequestToPublicacionEntity(PublicacionRequest publicacionRequest) {
        if ( publicacionRequest == null ) {
            return null;
        }

        PublicacionEntity publicacionEntity = new PublicacionEntity();

        publicacionEntity.setTitulo( publicacionRequest.getTitulo() );
        publicacionEntity.setDescripcion( publicacionRequest.getDescripcion() );
        publicacionEntity.setContenido( publicacionRequest.getContenido() );

        return publicacionEntity;
    }

    @Override
    public PublicacionResonse publicacionOpcionalToPublicacionResponse(PublicacionEntity publicacionOpcional) {
        if ( publicacionOpcional == null ) {
            return null;
        }

        PublicacionResonse publicacionResonse = new PublicacionResonse();

        publicacionResonse.setId( publicacionOpcional.getId() );
        publicacionResonse.setTitulo( publicacionOpcional.getTitulo() );
        publicacionResonse.setDescripcion( publicacionOpcional.getDescripcion() );
        publicacionResonse.setContenido( publicacionOpcional.getContenido() );

        return publicacionResonse;
    }

    @Override
    public List<PublicacionResonse> publicacionesEntitiesToPublicacionesResponses(List<PublicacionEntity> publicacionEntities) {
        if ( publicacionEntities == null ) {
            return null;
        }

        List<PublicacionResonse> list = new ArrayList<PublicacionResonse>( publicacionEntities.size() );
        for ( PublicacionEntity publicacionEntity : publicacionEntities ) {
            list.add( publicacionOpcionalToPublicacionResponse( publicacionEntity ) );
        }

        return list;
    }

    @Override
    public List<PublicacionResonse> publicacionesEntitiesToPublicacionesResponses(Page<PublicacionEntity> publicacionEntities) {
        if ( publicacionEntities == null ) {
            return null;
        }

        List<PublicacionResonse> list = new ArrayList<PublicacionResonse>();
        for ( PublicacionEntity publicacionEntity : publicacionEntities ) {
            list.add( publicacionOpcionalToPublicacionResponse( publicacionEntity ) );
        }

        return list;
    }
}

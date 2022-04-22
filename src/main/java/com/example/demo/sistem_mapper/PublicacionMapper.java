package com.example.demo.sistem_mapper;

import java.util.List;

import com.example.demo.sistem_model.PublicacionEntity;
import com.example.demo.sistem_request.PublicacionRequest;
import com.example.demo.sistem_response.PublicacionResonse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface PublicacionMapper {

    @Mapping(target = "id", ignore = true)
    public abstract PublicacionEntity publicacionRequestToPublicacionEntity(PublicacionRequest publicacionRequest);

    public abstract PublicacionResonse publicacionOpcionalToPublicacionResponse(PublicacionEntity publicacionOpcional);

    public abstract List<PublicacionResonse> publicacionesEntitiesToPublicacionesResponses(List<PublicacionEntity> publicacionEntities);

    public abstract List<PublicacionResonse> publicacionesEntitiesToPublicacionesResponses(Page<PublicacionEntity> publicacionEntities);
}

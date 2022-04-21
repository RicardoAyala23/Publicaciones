package com.example.demo.sistema_service;

import java.util.List;
import java.util.Optional;

import com.example.demo.exceptions.ResouceNotFoundException;
import com.example.demo.sistem_mapper.PublicacionMapper;
import com.example.demo.sistem_model.PublicacionEntity;
import com.example.demo.sistem_request.PublicacionRequest;
import com.example.demo.sistem_response.PublicacionResonse;
import com.example.demo.sistem_response.PublicacionResponseAtributos;
import com.example.demo.sistem_response.PublicacionResponseCreate;
import com.example.demo.sistema_repository.PublicacionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PublicacionServiceImpl implements PublicacionService {

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Autowired
    private PublicacionMapper publicacionMapper;

    @Override
    public PublicacionResponseCreate crearPublicacion(PublicacionRequest publicacionRequest) {

        PublicacionEntity publicacionEntity = publicacionMapper
                .publicacionRequestToPublicacionEntity(publicacionRequest);

        publicacionEntity = publicacionRepository.save(publicacionEntity);

        PublicacionResponseCreate publicacionResponseCreate = new PublicacionResponseCreate();

        publicacionResponseCreate.setId(publicacionEntity.getId());

        return publicacionResponseCreate;
    }

    @Override
    public List<PublicacionResonse> obtenerTodasLasPublicaciones() {

        List<PublicacionEntity> publicacionEntities = publicacionRepository.findAll();

        List<PublicacionResonse> publicacionResonses = publicacionMapper
                .publicacionesEntitiesToPublicacionesResponses(publicacionEntities);

        return publicacionResonses;
    }

    @Override
    public PublicacionResonse obtenerPorId(Long id) {
        Optional<PublicacionEntity> publicacionOpcional = publicacionRepository.findById(id);

        if (publicacionOpcional.isPresent()) {

            PublicacionResonse publicacionResonse = publicacionMapper
                    .publicacionOpcionalToPublicacionResponse(publicacionOpcional.get());

            return publicacionResonse;

        } else {

            throw new ResouceNotFoundException("", "Publicacion", "id", id);

        }

    }

    @Override
    public PublicacionResonse actualizarPublicacion(PublicacionRequest publicacionRequest, Long id) {

        Optional<PublicacionEntity> publicacionEntity = publicacionRepository.findById(id);

        publicacionEntity.get().setTitulo(publicacionRequest.getTitulo());

        publicacionEntity.get().setDescripcion(publicacionRequest.getDescripcion());

        publicacionEntity.get().setContenido(publicacionRequest.getContenido());

        PublicacionEntity publicacionEntitynueva = publicacionRepository.save(publicacionEntity.get());

        PublicacionResonse publicacionResonse = publicacionMapper
                .publicacionOpcionalToPublicacionResponse(publicacionEntitynueva);

        return publicacionResonse;
    }

    @Override
    public boolean eliminarPublicacion(Long id) {

        try {

            publicacionRepository.deleteById(id);

            return true;

        } catch (Exception e) {

            return false;

        }

    }

    @Override
    public PublicacionResponseAtributos obtenerTodasLasPublicacionesConPaginacion(int page, int size, String columna,
            String sortDir) {

        Sort sort = null;

        sort = Sort.by(columna);

        switch (sortDir) {

            case "ASC":
                sort = sort.ascending();
                break;
            case "DESC":

                sort = sort.descending();

                break;
        }

        Pageable pageable = PageRequest.of(page - 1, size,sort);

        Page<PublicacionEntity> publicacionEntities = publicacionRepository.findAll(pageable);

        List<PublicacionResonse> publicacionResonses = publicacionMapper
                .publicacionesEntitiesToPublicacionesResponses(publicacionEntities);

        PublicacionResponseAtributos publicacionResponseAtributos = new PublicacionResponseAtributos();

        publicacionResponseAtributos.setContenido(publicacionResonses);
        publicacionResponseAtributos.setNumeroPagina(publicacionEntities.getNumber() + 1);
        publicacionResponseAtributos.setMedidaPagina(publicacionEntities.getSize());
        publicacionResponseAtributos.setTotalElementos(publicacionEntities.getTotalElements());
        publicacionResponseAtributos.setTotalPaginas(publicacionEntities.getTotalPages());
        publicacionResponseAtributos.setUltima(publicacionEntities.isLast());

        return publicacionResponseAtributos;
    }

}

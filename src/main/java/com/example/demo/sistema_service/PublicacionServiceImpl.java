package com.example.demo.sistema_service;

import java.util.List;
import java.util.Optional;

import com.example.demo.especificaciones.Especificaciones;
import com.example.demo.exceptions.ResouceNotFoundException;
import com.example.demo.filtros.Filtro;
import com.example.demo.sistem_mapper.PublicacionMapper;
import com.example.demo.sistem_model.PublicacionEntity;
import com.example.demo.sistem_request.PublicacionRequest;
import com.example.demo.sistem_response.PublicacionResponseConComentarios;
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

    @Autowired
    private Especificaciones especificaciones;

    @Autowired
    private ComentarioServicio comentarioServicio;

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

        Pageable pageable = PageRequest.of(page - 1, size, sort);

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

    @Override
    public PublicacionResponseAtributos recuperarPublicacionesPorFiltros(List<Filtro> filtro,
            int page, int size,
            String columna, String sortDir) {

        Sort sorOrder = null;

        sorOrder = Sort.by(columna);

        switch (sortDir) {
            case "ASC":
                sorOrder = sorOrder.ascending();
                break;
            case "DESC":
                sorOrder = sorOrder.descending();
                break;
            default:
                sorOrder = sorOrder.descending();
                break;
        }

        Pageable pageable = PageRequest.of(page - 1, size, sorOrder);

        Page<PublicacionEntity> publicacionEntityPage = publicacionRepository
                .findAll(especificaciones.obtenerEspecificacionesPorFiltros(filtro), pageable);

        List<PublicacionEntity> publicacionEntityList = publicacionEntityPage.getContent();

        List<PublicacionResonse> publicacionResonses = null;

        publicacionResonses = publicacionMapper.publicacionesEntitiesToPublicacionesResponses(publicacionEntityList);

        PublicacionResponseAtributos publicacionResponseAtributos = new PublicacionResponseAtributos();

        publicacionResponseAtributos.setContenido(publicacionResonses);

        publicacionResponseAtributos.setNumeroPagina(publicacionEntityPage.getNumber() + 1);
        publicacionResponseAtributos.setMedidaPagina(publicacionEntityPage.getSize());
        publicacionResponseAtributos.setTotalElementos(publicacionEntityPage.getTotalElements());
        publicacionResponseAtributos.setTotalPaginas(publicacionEntityPage.getTotalPages());
        publicacionResponseAtributos.setUltima(publicacionEntityPage.isLast());

        return publicacionResponseAtributos;
    }

    @Override
    public PublicacionResponseConComentarios obtenerTodasLasPublicacionesConPaginacionYcomentarios(int page, int size,
            String columna, String sortDir) {

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

        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<PublicacionEntity> publicacionEntities = publicacionRepository.findAll(pageable);

        List<PublicacionResonse> publicacionResonses = publicacionMapper
                .publicacionesEntitiesToPublicacionesResponses(publicacionEntities);

        PublicacionResponseConComentarios publicacionConComentarios = new PublicacionResponseConComentarios();

        for (PublicacionResonse entrada : publicacionResonses) {

            publicacionConComentarios.setPublicacionResonses(publicacionResonses);

            publicacionConComentarios
                    .setComentarioResponse(comentarioServicio.obtenerComentariosPorPublicacionId(entrada.getId()));
        }

        return publicacionConComentarios;
    }

}

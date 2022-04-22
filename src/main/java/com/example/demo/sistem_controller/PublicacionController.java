package com.example.demo.sistem_controller;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.comunes.OperadorDeConsultas;
import com.example.demo.filtros.Filtro;
import com.example.demo.sistem_request.PublicacionRequest;
import com.example.demo.sistem_response.PublicacionResonse;
import com.example.demo.sistem_response.PublicacionResponseAtributos;
import com.example.demo.sistem_response.PublicacionResponseConComentarios;
import com.example.demo.sistem_response.PublicacionResponseCreate;
import com.example.demo.sistema_service.PublicacionService;
import com.example.demo.sistema_utilery.AppConstantes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inicio")
public class PublicacionController {

    @Autowired
    PublicacionService publicacionService;

    @PostMapping("/agregarProductos")
    public ResponseEntity<PublicacionResponseCreate> agregarProductos(
            @RequestBody PublicacionRequest publicacionRequest) {

        PublicacionResponseCreate publicacionResponseCreate = publicacionService.crearPublicacion(publicacionRequest);

        ResponseEntity<PublicacionResponseCreate> result = null;

        result = new ResponseEntity<>(publicacionResponseCreate, HttpStatus.CREATED);

        return result;
    }

    @PutMapping(path = "/actualizarPublicacion/{id}")
    public ResponseEntity<PublicacionResonse> actualizarPublicacion(@RequestBody PublicacionRequest publicacionRequest,
            @PathVariable("id") Long id) {

        PublicacionResonse publicacionResonse = publicacionService.actualizarPublicacion(publicacionRequest, id);

        ResponseEntity<PublicacionResonse> result = null;

        result = new ResponseEntity<>(publicacionResonse, HttpStatus.OK);

        return result;

    }

    @DeleteMapping(path = "/borrarPublicacion/{id}")
    public ResponseEntity<String> borrarPublicacion(@RequestParam("{id}") Long id) {

        boolean ok = publicacionService.eliminarPublicacion(id);

        ResponseEntity<String> result = null;

        if (ok) {

            result = new ResponseEntity<>("La publicacion se ha eliminado con exito. ", HttpStatus.OK);

            return result;

        } else {

            result = new ResponseEntity<>("Ha ocurrido un problema al intentar borrar la publicacion. ",
                    HttpStatus.BAD_REQUEST);

            return result;

        }

    }

    @GetMapping("/mostrarPublicaciones")
    public ResponseEntity<List<PublicacionResonse>> mostrarPublicaciones() {

        List<PublicacionResonse> publicacionResonses = publicacionService.obtenerTodasLasPublicaciones();

        ResponseEntity<List<PublicacionResonse>> result = null;

        result = new ResponseEntity<>(publicacionResonses, HttpStatus.OK);

        return result;
    }

    @GetMapping(path = "/buscarPorId/{id}")
    public ResponseEntity<PublicacionResonse> buscarPorId(@PathVariable("id") Long id) {

        PublicacionResonse publicacionResonse = publicacionService.obtenerPorId(id);

        ResponseEntity<PublicacionResonse> result = null;

        result = new ResponseEntity<>(publicacionResonse, HttpStatus.OK);

        return result;

    }

    @GetMapping("/obtenerPublicacionesConPaginacion")
    public ResponseEntity<PublicacionResponseAtributos> obtenerPublicacionesConPaginacion(
            @RequestParam(value = "pagina", defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int page,
            @RequestParam(value = "size", defaultValue = AppConstantes.MEDIDA_DE_PAGINA_POR_DEFECTO, required = false) int size,
            @RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false) String columna,
            @RequestParam(value = "sortDir", defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sortDir) {

        if (page < 1) {
            page = 1;
        }
        PublicacionResponseAtributos publicacionResponseAtributos = publicacionService
                .obtenerTodasLasPublicacionesConPaginacion(page, size, columna, sortDir);

        ResponseEntity<PublicacionResponseAtributos> result = null;

        result = new ResponseEntity<>(publicacionResponseAtributos, HttpStatus.OK);

        return result;

    }

    @GetMapping("/recuperarPorFiltros")
    public ResponseEntity<PublicacionResponseAtributos> recuperarPorFiltros(
            @RequestParam(value = "titulo", required = false) String titulo,
            @RequestParam(value = "descripcion", required = false) String descripcion,
            @RequestParam(value = "contenido", required = false) String contenido,
            @RequestParam(value = "pagina", defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int page,
            @RequestParam(value = "size", defaultValue = AppConstantes.MEDIDA_DE_PAGINA_POR_DEFECTO, required = false) int size,
            @RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false) String columna,
            @RequestParam(value = "sortDir", defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sortDir) {

        List<Filtro> filtros = new ArrayList<>();

        if (titulo != null && !titulo.isEmpty()) {

            Filtro filtroTitulo = Filtro.builder().campo("titulo").operador(OperadorDeConsultas.LIKE).valor(titulo)
                    .build();
            filtros.add(filtroTitulo);

        }

        if (descripcion != null && !descripcion.isEmpty()) {

            Filtro filtroDescripcion = Filtro.builder().campo("descripcion").operador(OperadorDeConsultas.LIKE)
                    .valor(descripcion).build();
            filtros.add(filtroDescripcion);
        }

        if (contenido != null && !contenido.isEmpty()) {

            Filtro filtroContenido = Filtro.builder().campo("contenido").operador(OperadorDeConsultas.LIKE)
                    .valor(contenido).build();
            filtros.add(filtroContenido);

        }

        if (page < 1) {

            page = 1;
        }

        PublicacionResponseAtributos publicacionResonses = null;

        if (filtros.isEmpty()) {

            // Si la lista esta vacia aplicamos el de traer toda la lista

            publicacionResonses = publicacionService.obtenerTodasLasPublicacionesConPaginacion(page, size, columna,
                    sortDir);

        } else {

            publicacionResonses = publicacionService.recuperarPublicacionesPorFiltros(filtros, page, size,
                    columna,
                    sortDir);

        }

        ResponseEntity<PublicacionResponseAtributos> result = null;

        result = new ResponseEntity<>(publicacionResonses, HttpStatus.OK);

        return result;
    }

    @GetMapping("/obtenerPublicacionesConPaginacionYcomentarios")
    public ResponseEntity<PublicacionResponseConComentarios> obtenerPublicacionesConPaginacionYcomentarios(
            @RequestParam(value = "pagina", defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int page,
            @RequestParam(value = "size", defaultValue = AppConstantes.MEDIDA_DE_PAGINA_POR_DEFECTO, required = false) int size,
            @RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false) String columna,
            @RequestParam(value = "sortDir", defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sortDir) {

        if (page < 1) {
            page = 1;
        }

        PublicacionResponseConComentarios publicacionResponseConComentarios = publicacionService
                .obtenerTodasLasPublicacionesConPaginacionYcomentarios(page, size, columna, sortDir);

        ResponseEntity<PublicacionResponseConComentarios> result = null;

        result = new ResponseEntity<>(publicacionResponseConComentarios, HttpStatus.OK);

        return result;

    }

}

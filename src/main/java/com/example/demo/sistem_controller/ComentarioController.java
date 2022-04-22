package com.example.demo.sistem_controller;

import java.util.List;

import com.example.demo.sistem_request.ComentarioRequest;
import com.example.demo.sistem_response.ComentarioResponse;
import com.example.demo.sistem_response.ComentarioResponseCreate;
import com.example.demo.sistema_service.ComentarioServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ComentarioController {

    @Autowired
    private ComentarioServicio comentarioService;

    @PostMapping(path = "/publicaciones/comentario")
    public ResponseEntity<ComentarioResponseCreate> guardarComentario(
            @RequestParam("publicacion_id") Long publicacionId, @RequestBody ComentarioRequest comentarioRequest) {

        ComentarioResponseCreate comentarioResponseCreate = comentarioService.crearComentario(publicacionId,
                comentarioRequest);

        ResponseEntity<ComentarioResponseCreate> result = null;

        result = new ResponseEntity<>(comentarioResponseCreate, HttpStatus.CREATED);

        return result;
    }

    // Comentario
    @GetMapping(path = "/listarComentariosPorPublicacion/{publicacionId}")
    public ResponseEntity<List<ComentarioResponse>> listarComentariosPorPublicacion(
            @RequestParam("publicacion_Id") Long publicacionId) {

        List<ComentarioResponse> comentarioResponses = comentarioService
                .obtenerComentariosPorPublicacionId(publicacionId);

        ResponseEntity<List<ComentarioResponse>> result = null;

        result = new ResponseEntity<>(comentarioResponses, HttpStatus.OK);

        return result;
    }

    @GetMapping(path = "/listarComentariosPorId/{publicacionId}/{comentarioId}")
    public ResponseEntity<ComentarioResponse> listarComentariosPorId(@RequestParam("publicacionId") Long publicacionId,
            @RequestParam("comentarioId") Long comentarioId) {

        ComentarioResponse comentarioResponse = comentarioService.obtenerComentarioPorId(publicacionId, comentarioId);
        ResponseEntity<ComentarioResponse> result = null;

        result = new ResponseEntity<>(comentarioResponse, HttpStatus.OK);

        return result;
    }

    @PutMapping("/actualizarComentarioPorId/{publicacionId}/{comentarioId}")
    public ResponseEntity<ComentarioResponse> actualizarComentarioPorId(
            @RequestParam("publicacionId") Long publicacionId, @RequestParam("comentarioId") Long comentarioId,
            @RequestBody ComentarioRequest comentarioRequest) {

        ComentarioResponse comentarioResponse = comentarioService.actualizarComentario(publicacionId, comentarioId,
                comentarioRequest);

        ResponseEntity<ComentarioResponse> result = null;

        result = new ResponseEntity<>(comentarioResponse, HttpStatus.CREATED);

        return result;
    }

    @DeleteMapping("/borrarComentarioEncontradoPorId/{publicacionId}/{comentarioId}")
    public ResponseEntity<String> borrarComentarioEncontradoPorId(@RequestParam("publicacionId") Long publicacionId,
            @RequestParam("comentarioId") Long comentarioId) {

        Boolean ok = comentarioService.borrarComentario(publicacionId, comentarioId);

        ResponseEntity<String> result = null;

        if (ok) {

            result = new ResponseEntity<>("El comentario se ha eliminado con exito. ", HttpStatus.OK);

            return result;

        } else {

            result = new ResponseEntity<>("Ha ocurrido un problema al intentar borrar el comentario. ",
                    HttpStatus.BAD_REQUEST);

            return result;

        }
    }

}
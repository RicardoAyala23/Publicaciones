package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogAppExceptions extends RuntimeException {

    private static final Long serialVersionUID = 1L;

    private HttpStatus estado;

    private String mensaje;

    public BlogAppExceptions(HttpStatus estado, String mensaje, String mensaje1){

        this.estado = estado;

        this.mensaje = mensaje;

        this.mensaje = mensaje1;

    }
    
}

package com.example.demo.sistem_request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublicacionRequest {
    
    private Long id;

    private String titulo;

    private String descripcion;

    private String contenido;
}

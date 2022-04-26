package com.example.demo.sistem_request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


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

    @NotEmpty
    @NotBlank
    @Size(min = 2, message = "El titulo de la publicacion deberia tener al menos 15 caracteres. ")
    private String titulo;

    @NotEmpty
    @NotBlank
    @Size(min = 2, message = "La descripcion de la publicacion deberia tener al menos 25 caracteres. ")
    private String descripcion;

    @NotBlank
    @NotEmpty
    private String contenido;
}

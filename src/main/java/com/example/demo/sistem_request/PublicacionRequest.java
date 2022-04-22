package com.example.demo.sistem_request;

import javax.validation.constraints.NotBlank;

import com.example.demo.sistema_utilery.AppConstantes;

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

    @NotBlank(message = AppConstantes.ERRCODE_NAME_NULL)
    private String titulo;

    @NotBlank(message = AppConstantes.ERRCODE_DESCRIPTION_NULL)
    private String descripcion;

    @NotBlank(message = AppConstantes.ERRCODE_CONTENT_NULL)
    private String contenido;
}

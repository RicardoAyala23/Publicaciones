package com.example.demo.sistem_request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.example.demo.sistema_utilery.AppConstantes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioRequest {

    private Long id;

    @NotBlank(message = AppConstantes.ERRCODE_NAME_NULL)
    private String nombre;

    @Email
    private String email;

    @NotBlank(message =AppConstantes.ERRCODE_FIELD_NULL)
    private String cuerpo;
}

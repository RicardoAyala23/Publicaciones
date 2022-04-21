package com.example.demo.sistem_request;

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

    private String nombre;

    private String email;

    private String cuerpo;
}

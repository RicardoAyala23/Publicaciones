package com.example.demo.sistem_response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ComentarioResponse {
    
    private Long id;

    private String nombre;

    private String email;
    
    private String cuerpo;
}

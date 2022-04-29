package com.example.demo.sistem_request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRegistroRequest {
    
    private Long id;

    private String nombre;

    private String userName;

    private String email;

    private String password;
}

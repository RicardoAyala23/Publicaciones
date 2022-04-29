package com.example.demo.seguridad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthResponse {
    
    private String tokenDeAcceso;

    private String tipoDeToken = "Bearer" ;

    public JwtAuthResponse(String tokenDeAcceso){

        this.tokenDeAcceso = tokenDeAcceso;

    }
}

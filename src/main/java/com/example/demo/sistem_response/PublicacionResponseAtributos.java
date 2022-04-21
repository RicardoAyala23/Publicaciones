package com.example.demo.sistem_response;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublicacionResponseAtributos {
    
    private List<PublicacionResonse> contenido;

    private int numeroPagina;

    private int medidaPagina;

    private Long totalElementos;

    private int totalPaginas;

    private boolean ultima;
}

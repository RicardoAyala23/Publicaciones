package com.example.demo.filtros;

import com.example.demo.comunes.OperadorDeConsultas;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class Filtro {

    private String campo;

    private OperadorDeConsultas operador;

    private String valor;
    
}

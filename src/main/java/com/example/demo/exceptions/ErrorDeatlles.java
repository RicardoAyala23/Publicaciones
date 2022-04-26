package com.example.demo.exceptions;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorDeatlles {

    private Date marcaTiempo;

    private String mensaje;

    private String detalles;
    
}

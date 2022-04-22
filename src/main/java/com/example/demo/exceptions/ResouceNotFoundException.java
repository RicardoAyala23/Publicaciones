package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResouceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1;

    private String nombreRecurso;

    private String nombreDelCampo;

    private Long valorDelCampo;

    public ResouceNotFoundException(String message, String nombreRecurso, String nombreDelCampo, Long valorDelCampo) {
        super(String.format("%s No encontontrada con  : %s : '%s'", nombreRecurso, nombreDelCampo, valorDelCampo));
        this.nombreRecurso = nombreRecurso;
        this.nombreDelCampo = nombreDelCampo;
        this.valorDelCampo = valorDelCampo;
    }

}

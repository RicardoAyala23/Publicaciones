package com.example.demo.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExecptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResouceNotFoundException.class)
    public ResponseEntity<ErrorDeatlles> manejarResourceNotFoundExecptions(ResouceNotFoundException exception,
            WebRequest webRequest) {

        ErrorDeatlles errorDeatlles = new ErrorDeatlles(new Date(), exception.getMessage(),
                webRequest.getDescription(false));

        return new ResponseEntity<>(errorDeatlles, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BlogAppExceptions.class)
    public ResponseEntity<ErrorDeatlles> manejarBlogAppExceptions(BlogAppExceptions exception,
            WebRequest webRequest) {

        ErrorDeatlles errorDeatlles = new ErrorDeatlles(new Date(), exception.getMessage(),
                webRequest.getDescription(false));

        return new ResponseEntity<>(errorDeatlles, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDeatlles> manejarExecutable(Exception exception,
            WebRequest webRequest) {

        ErrorDeatlles errorDeatlles = new ErrorDeatlles(new Date(), exception.getMessage(),
                webRequest.getDescription(false));

        return new ResponseEntity<>(errorDeatlles, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Este metodo nos sirve para ir manejando las validaciones en el request
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {

            String nombreCampo = ((FieldError) error).getField();

            String mensaje = error.getDefaultMessage();

            errores.put(nombreCampo, mensaje);
        });

        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }

}

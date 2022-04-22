package com.example.demo.sistem_response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PublicacionResponseConComentarios {
    
    private List<PublicacionResonse> publicacionResonses;

    private List<ComentarioResponse> ComentarioResponse;
}

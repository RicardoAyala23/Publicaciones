package com.example.demo.sistem_controller;

import java.util.Collections;

import com.example.demo.seguridad.JwtAuthResponse;
import com.example.demo.seguridad.JwtTokenProveder;
import com.example.demo.sistem_model.Rol;
import com.example.demo.sistem_model.Usuario;
import com.example.demo.sistem_request.LoginRequest;
import com.example.demo.sistem_request.UsuarioRegistroRequest;
import com.example.demo.sistema_repository.RolRepository;
import com.example.demo.sistema_repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProveder jwtTokenProveder;

    @PostMapping(path = "/iniciarSesion")
    private ResponseEntity<JwtAuthResponse> authenticationUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Obtenemos el token del jwtTokenProvider
        String token = jwtTokenProveder.generarToken(authentication);

        return new ResponseEntity<>(new JwtAuthResponse(token), HttpStatus.OK);
    }

    @PostMapping(path = "registrar_usuario")
    private ResponseEntity<?> registrarUsuario(@RequestBody UsuarioRegistroRequest usuarioRegistroRequest) {

        if (usuarioRepository.existsByUsername(usuarioRegistroRequest.getUserName())) {

            return new ResponseEntity<>("Este nombre de usuario ya existe. ", HttpStatus.BAD_REQUEST);

        }
        if (usuarioRepository.existsByEmail(usuarioRegistroRequest.getEmail())) {

            return new ResponseEntity<>("Este email de usuario ya existe. ", HttpStatus.BAD_REQUEST);

        }

        Usuario usuario = Usuario.builder().nombre(usuarioRegistroRequest.getNombre())
                .username(usuarioRegistroRequest.getUserName())
                .email(usuarioRegistroRequest.getEmail())
                .paswwrod(passwordEncoder.encode(usuarioRegistroRequest.getPassword()))
                .build();

        Rol roles = rolRepository.findByNombre("ROLE_ADMIN").get();

        usuario.setRoles(Collections.singleton(roles));

        usuarioRepository.save(usuario);

        return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.CREATED);

    }
}

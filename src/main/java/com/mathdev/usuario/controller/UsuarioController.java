package com.mathdev.usuario.controller;

import com.mathdev.usuario.business.UsuarioService;
import com.mathdev.usuario.business.dto.UsuarioDTO;
import com.mathdev.usuario.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public String login(@RequestBody UsuarioDTO userDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getEmail(),
                        userDTO.getSenha())

        );
        return "Bearer " + jwtUtil.generateToken(authentication.getName());
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> createUser(@RequestBody UsuarioDTO userDTO){
        return ResponseEntity.ok(usuarioService.createUser(userDTO));
    }

    @GetMapping
    public ResponseEntity<UsuarioDTO> findUserByEmail(@RequestParam("email") String email){
        return ResponseEntity.ok(usuarioService.findByEmail(email));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findUserById(@PathVariable("id") Long id){
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UsuarioDTO>> findUserById(){
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @DeleteMapping("/{email}")
    public void deleteUserByEmail(@PathVariable("email") String email){
        usuarioService.deleteByEmail(email);
    }
}

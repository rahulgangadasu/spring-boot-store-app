package com.storeapi.store.controllers;

import com.storeapi.store.dtos.JwtResponse;
import com.storeapi.store.dtos.LoginRequest;
import com.storeapi.store.dtos.UserDto;
import com.storeapi.store.mappers.UserMapper;
import com.storeapi.store.repositories.UserRepository;
import com.storeapi.store.services.JwtService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserMapper userMapper;
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(
            @Valid @RequestBody LoginRequest request
    ) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                ));

        var token = jwtService.generateToken(request.getEmail());
        return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
    }

    @GetMapping("/current")
    public ResponseEntity<UserDto> currentUser(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = null;
        if (authentication != null) {
            email = (String) authentication.getPrincipal();
        }

        var user = userRepository.findByEmail(email).orElse(null);
        if(user == null)
            return ResponseEntity.notFound().build();
        var userDto = userMapper.toDto(user);
        return  ResponseEntity.ok(userDto);
    }

    @PostMapping("/validate")
    public boolean validate(@RequestHeader("Authorization") String authHeader){
        var token =  authHeader.replace("Bearer ", "");
        return jwtService.validateToken(token);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Void> handleBadCredentialsException(){
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}

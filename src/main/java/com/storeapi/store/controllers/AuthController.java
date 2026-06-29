package com.storeapi.store.controllers;

import com.storeapi.store.dtos.JwtResponse;
import com.storeapi.store.dtos.LoginRequest;
import com.storeapi.store.services.JwtService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

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

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Void> handleBadCredentialsException(){
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}

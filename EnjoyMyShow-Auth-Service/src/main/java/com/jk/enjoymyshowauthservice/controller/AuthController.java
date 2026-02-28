package com.jk.enjoymyshowauthservice.controller;

import com.jk.enjoymyshowauthservice.dto.AuthResponseDto;
import com.jk.enjoymyshowauthservice.dto.LoginRequestDto;
import com.jk.enjoymyshowauthservice.dto.RegisterRequestDto;
import com.jk.enjoymyshowauthservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


    @RestController
    @RequestMapping("/auth")
    @RequiredArgsConstructor
    public class AuthController
    {
        private final AuthService authService;

        @PostMapping("/register")
        @ResponseStatus(HttpStatus.CREATED)
        public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterRequestDto registerRequestDto)
        {
            authService.register(registerRequestDto);
            return  ResponseEntity.ok("User Registered Successfully");
        }

        @PostMapping("/login")
        public AuthResponseDto loginUser(@Valid @RequestBody LoginRequestDto loginRequestDto)
        {
            return authService.login(loginRequestDto);
        }



    }



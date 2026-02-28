package com.jk.enjoymyshowauthservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthResponseDto
{
    private  String token;

    private  long expiresIn;
}

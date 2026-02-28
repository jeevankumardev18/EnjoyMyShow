package com.jk.enjoymyshowauthservice.service;

import com.jk.enjoymyshowauthservice.dto.AuthResponseDto;
import com.jk.enjoymyshowauthservice.dto.LoginRequestDto;
import com.jk.enjoymyshowauthservice.dto.RegisterRequestDto;
import com.jk.enjoymyshowauthservice.entity.UserCredential;
import com.jk.enjoymyshowauthservice.entity.UserRole;
import com.jk.enjoymyshowauthservice.entity.UserStatus;
import com.jk.enjoymyshowauthservice.repository.UserRepository;
import com.jk.enjoymyshowauthservice.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService
{
    private final UserRepository repo;
    private final JwtProvider jwtProvider;
    private  final PasswordEncoder passwordEncoder;

    public void register(RegisterRequestDto requestDto)
    {
        if(repo.existsByEmail(requestDto.getEmail()))
        {
            throw new RuntimeException("An account already exists with this email. Please log in.");
        }

        UserCredential user=  UserCredential.builder()
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .role(UserRole.valueOf(requestDto.getRole()))
                .status(UserStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .build();

        repo.save(user);

    }

    public AuthResponseDto login(LoginRequestDto loginRequestDto)
    {
        UserCredential user=repo.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(()-> new IllegalArgumentException("Invalid email or password"));

        if(user.getStatus()== UserStatus.BLOCKED)
        {
            throw new RuntimeException("User is Blocked");
        }
        boolean matched= passwordEncoder.matches(loginRequestDto.getPassword(),user.getPassword());
        if(!matched)
        {
            throw new IllegalArgumentException("Invalid email or password");
        }

        String token= jwtProvider.generateToken(user.getId(), user.getEmail(), user.getRole().name());
        return new AuthResponseDto(token,1800);
    }

}

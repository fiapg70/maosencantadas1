package com.maosencantadas.api.controller;

import com.maosencantadas.api.dto.UserResponse;
import com.maosencantadas.api.mapper.UserMapper;
import com.maosencantadas.exception.RecursoEncontradoException;
import com.maosencantadas.infra.security.TokenService;
import com.maosencantadas.model.domain.user.*;
import com.maosencantadas.model.repository.UserRepository;
import com.maosencantadas.util.RestUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Slf4j
@RestController()
@RequiredArgsConstructor
@RequestMapping("auth")
@CrossOrigin(origins = "*", allowedHeaders = "", maxAge = 3600)
@Tag(name = "Authentication", description = "Autenticação Usuários")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserMapper userMapper;


    @Operation(summary = "Login do usuário",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
            }
    )
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @Operation(summary = "Registro do usuário",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Registro do Usuário encontrado"),
                    @ApiResponse(responseCode = "404", description = "Registro Usuário não encontrado")
            }
    )
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid RegisterDTO data) {
        if (this.repository.findByLogin(data.login()).isPresent()) {
            throw new RecursoEncontradoException("Usuário já cadastrado com o login: " + data.login());
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        UserRole userRole = UserRole.getUserRole(data.role());
        User newUser = new User(data.login(), encryptedPassword, userRole);

        User saved = this.repository.save(newUser);

        UserResponse userResponse = userMapper.toUserResponse(saved);
        URI location = RestUtils.getUri(saved.getId());
        return ResponseEntity.created(location).body(userResponse);
    }
}
package com.adriano.clinical.controller;

import com.adriano.clinical.domain.dto.request.LoginRequest;
import com.adriano.clinical.domain.dto.response.LoginResponse;
import com.adriano.clinical.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Login Endpoints", description = "clinica", tags = {"Login Endpoint"})
@RequestMapping("/auth")
@AllArgsConstructor
@RestController
public class LoginController {

    private LoginService loginService;

    @ApiOperation(value = "Gera o token de autenticação")
    @PostMapping
    public ResponseEntity<LoginResponse> auth(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(loginService.auth(loginRequest));
    }
}

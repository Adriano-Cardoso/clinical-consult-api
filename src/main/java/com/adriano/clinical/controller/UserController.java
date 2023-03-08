package com.adriano.clinical.controller;

import com.adriano.clinical.domain.dto.request.UserRequest;
import com.adriano.clinical.domain.dto.response.UserResponse;
import com.adriano.clinical.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "User Endpoint", description = "Endpoints User", tags = {"User Endpoint"})
@RequestMapping("/users")
@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @ApiOperation(value = "Cria um novo usuário")
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userRequest));
    }
}

package com.adriano.clinical.domain.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class LoginRequest {

    @NotNull(message = "O campo 'username' esta incorreto no corpo da requisicao")
    @ApiModelProperty(position = 1, required = false, value = "username do usuario", name = "username", dataType = "String", example = "Admin")
    private String username;

    @NotNull(message = "O campo 'email' esta incorreto no corpo da requisicao")
    @Email(message = "Formato do campo 'email'esta incorreto no corpo da requisicao")
    @ApiModelProperty(position = 2, required = false, value = "login do usuario", name = "email", dataType = "String", example = "admin@email.com")
    private String email;

    @NotNull(message = "O campo 'password' esta incorreto no corpo da requisicao!")
    @NotEmpty(message = "O campo 'password' esta vazio no corpo da requisicao!")
    @ApiModelProperty(position = 3, required = false, value = "senha do usuario", name = "password", dataType = "String", example = "123456")
    private String password;
}

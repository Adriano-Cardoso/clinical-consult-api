package com.adriano.clinical.exception;

import org.springframework.http.HttpStatus;

public enum Message {
    EXISTIS_DOCTOR("O médico já existe em nossa base de dados", HttpStatus.BAD_REQUEST),
    NOT_FOUND_ID("O Médico não existe em nossa base de dados ", HttpStatus.NOT_FOUND),
    NOT_FOUND_CRM("O CRM nâo existe ou CRM está incorreto", HttpStatus.BAD_REQUEST ),
    NOT_FOT_USER_PERMISSION("Não autorizado", HttpStatus.FORBIDDEN),
    TOKEN_ERROR("Token inválido", HttpStatus.FORBIDDEN),
    IS_PRESENT_USER("O TOKEN existe para esse usuário ", HttpStatus.BAD_REQUEST),
    NAME_PROFILE_NOT_FOUND("Perfil não encontrado ", HttpStatus.NOT_FOUND);

    private String value;
    private String description;
    private HttpStatus statusCode;

    private Message(String value, HttpStatus statusCode) {
        this.value = value;
        this.statusCode = statusCode;
    }

    private Message(String value, String description, HttpStatus statusCode) {
        this.value = value;
        this.description = description;
        this.statusCode = statusCode;
    }

    private Message(String value) {
        this.value = value;
    }

    public String getMessage() {
        return this.value;
    }

    public HttpStatus getStatus() {
        return this.statusCode;
    }

    public String getDescription() {
        return description;
    }

    public BusinessException asBusinessException() {
        return BusinessException.builder().httpStatusCode(this.getStatus())
                .code(String.valueOf(this.getStatus().value())).message(this.getMessage())
                .description(this.getDescription()).build();
    }
}

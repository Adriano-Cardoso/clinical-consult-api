package com.adriano.clinical.exception;

import org.springframework.http.HttpStatus;

public enum Message {
    EXISTIS_DOCTOR("O médico já existe em nossa base de dados", HttpStatus.BAD_REQUEST),

    EXISTIS_PATIENT("O paciente já existe em nossa base", HttpStatus.BAD_REQUEST),
    NOT_FOUND_ID("O Médico não existe em nossa base de dados ", HttpStatus.NOT_FOUND),
    NOT_FOUND_CRM("O CRM nâo existe ou CRM está incorreto", HttpStatus.BAD_REQUEST),
    NOT_FOT_USER_PERMISSION("Não autorizado", HttpStatus.FORBIDDEN),
    TOKEN_ERROR("Token inválido", HttpStatus.FORBIDDEN),
    IS_PRESENT_USER("O TOKEN existe para esse usuário ", HttpStatus.BAD_REQUEST),
    NAME_PROFILE_NOT_FOUND("Perfil não encontrado ", HttpStatus.NOT_FOUND),
    OUT_OF_OPERATING_HOURS("Consulta fora do horário de funcionamento da clínica", HttpStatus.BAD_REQUEST),
    APPOINTMENT_TIME("Consulta deve ser agendada com antecedência mínima de 30 minutos", HttpStatus.BAD_REQUEST),
    INACTIVE_DOCTOR("Consulta nâo pode ser agendada com o médico, pois o mesmo se encontra inativo", HttpStatus.BAD_REQUEST),
    PATIENT_CONSULT("Paciente já possui uma consulta agendada nesse dia", HttpStatus.BAD_REQUEST),
    DOCTOR_CONSULT("O médico já possui outra consulta agendada nesse mesmo horário", HttpStatus.BAD_REQUEST),
    PATIENT_INACTIVE("Consulta não pode ser agendada com paciente excluido", HttpStatus.BAD_REQUEST),
    SPECIALTY("Especialidade obrigatória quando o médico não for escolhido", HttpStatus.BAD_REQUEST),
    NOT_FOUND_ID_CONSULTATION("Consulta não encontrada", HttpStatus.NOT_FOUND),
    CANCEL_INVALID("Motivo de cancelamento inválido", HttpStatus.BAD_REQUEST);

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

package com.adriano.clinical.domain.dto.request;

import com.adriano.clinical.domain.enums.Specialty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationRequest {

    @JsonIgnore
    private String namePatient;

    @JsonIgnore
    private String nameDoctor;

    @ApiModelProperty(position = 1, value = "ID do Paciente", name = "patientId", dataType = "Long", example = "2")
    private Long patientId;

    @ApiModelProperty(position = 2, value = "ID do médico", name = "doctorId", dataType = "Long", example = "2")
    private Long doctorId;

    @ApiModelProperty(position = 1, value = "Data do agendamento da consulta", name = "dateGender", dataType = "LocalDateTime")
    private LocalDateTime dateGender;

    @ApiModelProperty(position = 1, value = "Especialidade do médico", name = "specialty", dataType = "Specialty", example = "DERMATOLOGY")
    private Specialty specialty;

}



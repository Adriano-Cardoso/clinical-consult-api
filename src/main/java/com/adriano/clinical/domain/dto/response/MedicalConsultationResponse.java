package com.adriano.clinical.domain.dto.response;

import com.adriano.clinical.domain.enums.Specialty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalConsultationResponse {

    private Long consultationId;

    @JsonIgnore
    private String namePatient;

    @JsonIgnore
    private String nameDoctor;

    private Long doctorId;

    private Long patientId;

    private Specialty specialty;

    private LocalDateTime dateTime;


}

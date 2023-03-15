package com.adriano.clinical.domain.dto.response;

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

    private String namePatient;

    private String nameDoctor;

    private LocalDateTime dateTime;
}

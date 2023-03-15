package com.adriano.clinical.domain.dto.request;

import com.adriano.clinical.domain.enums.Specialty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationRequest {

    private String namePatient;

    private String nameDoctor;

    private LocalDateTime dateTime;

    private Specialty specialty;

}



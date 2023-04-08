package com.adriano.clinical.domain.dto.request;

import com.adriano.clinical.domain.enums.Specialty;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private Long patientId;

    private Long doctorId;

    private LocalDateTime dateGender;

    private Specialty specialty;

}



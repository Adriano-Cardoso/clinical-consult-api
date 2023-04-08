package com.adriano.clinical.validation;

import com.adriano.clinical.domain.dto.request.ConsultationRequest;

public interface ValidationSchedulingConsultation {

    void validate(ConsultationRequest consultationRequest);
}

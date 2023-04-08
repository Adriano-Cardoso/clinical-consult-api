package com.adriano.clinical.validation;

import com.adriano.clinical.domain.dto.request.ConsultationCancelRequest;
import com.adriano.clinical.domain.dto.request.ConsultationRequest;

public interface ValidationCancelConsultation {

    void validate(ConsultationCancelRequest consultationCancelRequest);
}

package com.adriano.clinical.validation;

import com.adriano.clinical.domain.dto.request.ConsultationCancelRequest;
import com.adriano.clinical.exception.Message;

public class ValidCancellationReason implements ValidationCancelConsultation {

    public void validate(ConsultationCancelRequest consultationCancelRequest) {
        if (!consultationCancelRequest.getCancellationReason().equals("paciente desistiu")
                || !consultationCancelRequest.getCancellationReason().equals("médico cancelou")
                || !consultationCancelRequest.getCancellationReason().equals("outros")) {

            throw Message.CANCEL_INVALID.asBusinessException();

        }

    }
}

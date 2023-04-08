package com.adriano.clinical.validation;

import com.adriano.clinical.domain.dto.request.ConsultationCancelRequest;
import com.adriano.clinical.exception.Message;

import java.time.LocalDateTime;

public class ValidTimeCancellation implements ValidationCancelConsultation {

    public void validate(ConsultationCancelRequest consultationCancelRequest) {

        LocalDateTime dateNow = LocalDateTime.now();

        LocalDateTime limit = dateNow.plusHours(24);

        consultationCancelRequest.getDateCancel().isAfter(limit);

        if (consultationCancelRequest.getDateCancel().compareTo(dateNow) <= 24){
            throw Message.CANCEL_INVALID.asBusinessException();
        }
    }
}

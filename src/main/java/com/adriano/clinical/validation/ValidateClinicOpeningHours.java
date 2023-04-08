package com.adriano.clinical.validation;

import com.adriano.clinical.domain.dto.request.ConsultationRequest;
import com.adriano.clinical.exception.Message;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidateClinicOpeningHours implements ValidationSchedulingConsultation {

    public void validate(ConsultationRequest consultationRequest) {
        LocalDateTime consultationDate = consultationRequest.getDateGender();
        LocalDateTime currentDate = LocalDateTime.now();
        long differenceInMinutes = Duration.between(currentDate,consultationDate).toMinutes();

        if (differenceInMinutes < 30){
            throw  Message.APPOINTMENT_TIME.asBusinessException();
        }
    }
}

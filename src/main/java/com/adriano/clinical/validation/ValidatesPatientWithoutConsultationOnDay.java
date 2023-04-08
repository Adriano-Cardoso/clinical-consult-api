package com.adriano.clinical.validation;

import com.adriano.clinical.domain.Patient;
import com.adriano.clinical.domain.dto.request.ConsultationRequest;
import com.adriano.clinical.exception.Message;
import com.adriano.clinical.repository.ConsultationRepository;
import com.adriano.clinical.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class ValidatesPatientWithoutConsultationOnDay implements ValidationSchedulingConsultation {

    private ConsultationRepository consultationRepository;

    private PatientRepository patientRepository;

    public void validate(ConsultationRequest consultationRequest) {
        LocalDateTime firstHour = consultationRequest.getDateGender().withHour(7);
        LocalDateTime lastHour = consultationRequest.getDateGender().withHour(18);
        Patient patient = patientRepository.findById(consultationRequest.getPatientId()).get();
        boolean patientConsultDay = this.consultationRepository.existsByPatientAndDateBetween(patient, firstHour, lastHour);

        if (patientConsultDay) {
            throw Message.PATIENT_CONSULT.asBusinessException();
        }

    }
}

package com.adriano.clinical.validation;

import com.adriano.clinical.domain.dto.request.ConsultationRequest;
import com.adriano.clinical.exception.Message;
import com.adriano.clinical.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ValidatePatientActive  implements ValidationSchedulingConsultation{

    private PatientRepository patientRepository;

    public void validate(ConsultationRequest consultationRequest) {
        Long patient = patientRepository.findById(consultationRequest.getPatientId()).get().getPatientId();

        Boolean patientIsActive = this.patientRepository.findActiveById(patient);

        if (!patientIsActive){
            throw Message.PATIENT_INACTIVE.asBusinessException();
        }

    }
}

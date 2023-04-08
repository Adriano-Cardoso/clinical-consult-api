package com.adriano.clinical.validation;

import com.adriano.clinical.domain.dto.request.ConsultationRequest;
import com.adriano.clinical.exception.Message;
import com.adriano.clinical.repository.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ValidateDoctorActive implements ValidationSchedulingConsultation {

    private DoctorRepository doctorRepository;

    public void validate(ConsultationRequest consultationRequest) {
        //parar aqui
        Long doctor = doctorRepository.findById(consultationRequest.getDoctorId()).get().getDoctorId();
        Boolean doctorActive = doctorRepository.findActiveById(doctor);

        if (!doctorActive) {
            throw Message.INACTIVE_DOCTOR.asBusinessException();
        }
    }

}

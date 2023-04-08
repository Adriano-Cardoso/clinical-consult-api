package com.adriano.clinical.validation;

import com.adriano.clinical.domain.Doctor;
import com.adriano.clinical.domain.dto.request.ConsultationRequest;
import com.adriano.clinical.exception.Message;
import com.adriano.clinical.repository.ConsultationRepository;
import com.adriano.clinical.repository.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ValidateIfDoctorHasAnAppointmentAtThatTime  implements ValidationSchedulingConsultation{

    private ConsultationRepository consultationRepository;

    private DoctorRepository doctorRepository;

    public void validate(ConsultationRequest consultationRequest) {
        Doctor doctor = doctorRepository.findById(consultationRequest.getDoctorId()).get();
        boolean doctorAppointmentSameTime = this.consultationRepository.existsByDoctorAndDate(doctor, consultationRequest.getDateGender());
        if (doctorAppointmentSameTime){
            throw Message.DOCTOR_CONSULT.asBusinessException();
        }

    }
}

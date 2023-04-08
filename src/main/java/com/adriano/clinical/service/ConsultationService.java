package com.adriano.clinical.service;

import com.adriano.clinical.domain.Consultation;
import com.adriano.clinical.domain.Doctor;
import com.adriano.clinical.domain.Patient;
import com.adriano.clinical.domain.dto.request.ConsultationCancelRequest;
import com.adriano.clinical.domain.dto.request.ConsultationRequest;
import com.adriano.clinical.domain.dto.response.CancelConsultationResponse;
import com.adriano.clinical.domain.dto.response.MedicalConsultationResponse;
import com.adriano.clinical.exception.Message;
import com.adriano.clinical.repository.ConsultationRepository;
import com.adriano.clinical.repository.DoctorRepository;
import com.adriano.clinical.repository.PatientRepository;
import com.adriano.clinical.validation.ValidTimeCancellation;
import com.adriano.clinical.validation.ValidationSchedulingConsultation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ConsultationService {

    private ConsultationRepository consultationRepository;

    private List<ValidTimeCancellation> validTimeCancellations;

    private PatientRepository patientRepository;

    private DoctorRepository doctorRepository;

    private List<ValidationSchedulingConsultation> validationSchedulingConsultations;

    public MedicalConsultationResponse scheduleConsultation(ConsultationRequest consultationRequest) {

        Patient patient = this.patientRepository.findById(consultationRequest.getPatientId()).get();

        validationSchedulingConsultations.forEach(validationSchedulingConsultation -> {
            validationSchedulingConsultation.validate(consultationRequest);
        });

        Doctor doctor = selectDoctor(consultationRequest);

        Consultation consultation = Consultation.of(consultationRequest);

        this.consultationRepository.save(consultation);

        return consultation.toResponse();
    }

    public CancelConsultationResponse cancelConsultation(Long consultationId, ConsultationCancelRequest consultationCancelRequest){

        Consultation consultation = this.consultationRepository.findById(consultationId)
                .orElseThrow(Message.NOT_FOUND_ID_CONSULTATION::asBusinessException);

        validTimeCancellations.forEach(validTimeCancellation -> {
            validTimeCancellation.validate(consultationCancelRequest);
        });

        consultation.setCancellationReason(consultationCancelRequest.getCancellationReason());

        consultationRepository.save(consultation);

        return consultation.toCancelConsultationResponse();
    }

    private Doctor selectDoctor(ConsultationRequest consultationRequest) {
        if (consultationRequest.getSpecialty() == null) {
            throw Message.SPECIALTY.asBusinessException();
        }
        List<Doctor> doctors = doctorRepository.chooseRandomDoctorFreeOnDate(consultationRequest.getSpecialty(), consultationRequest.getDateGender());
        Doctor doctor = doctors.isEmpty() ? null : doctors.get(0);

        doctor = doctorRepository.findByNames(consultationRequest.getNameDoctor());

        return doctor;
    }


}

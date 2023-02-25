package com.adriano.clinical.service;

import com.adriano.clinical.domain.Patient;
import com.adriano.clinical.domain.dto.request.PatientRequest;
import com.adriano.clinical.domain.dto.request.PatientUpdateRequest;
import com.adriano.clinical.domain.dto.response.PatientResponse;
import com.adriano.clinical.domain.dto.response.SearchDoctorResponse;
import com.adriano.clinical.domain.dto.response.SearchPatientResponse;
import com.adriano.clinical.exception.Message;
import com.adriano.clinical.repository.PatientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

import static com.adriano.clinical.exception.Message.EXISTIS_DOCTOR;

@Service
@Slf4j
@Validated
@AllArgsConstructor
public class PatientService {

    private PatientRepository patientRepository;

    public PatientResponse createPatient(@Valid PatientRequest patientRequest) {

        this.patientRepository.findByName(patientRequest.getName()).ifPresent(pat -> {
            throw EXISTIS_DOCTOR.asBusinessException();
        });

        Patient patient = Patient.of(patientRequest);

        this.patientRepository.save(patient);

        log.info("method=createPatient PatientId={} name={} email={} phone={} isActivePatient{} address={}",
                patient.getPatientId(), patient.getName(), patient.getEmail(), patient.getPhone(),
                patient.isActivePatient(), patient.getAddress());
        return patient.toResponse();
    }


    public Page<SearchPatientResponse> searchRegisteredPatients(int page, int limit, String name) {

        Pageable pageable = PageRequest.of(page, limit);

        log.info("method=searchRegisteredPatients Name={} limit={}",
                name, limit);
        return patientRepository.searchRegisteredPatients(pageable, name);
    }

    @Validated
    @Transactional
    public PatientResponse updatePatient(Long patientId, @Valid PatientUpdateRequest patientUpdateRequest) {
        Patient patient = this.patientRepository.findById(patientId)
                .orElseThrow(Message.NOT_FOUND_ID::asBusinessException);

        patient.updatePatient(patientUpdateRequest);

        log.info("method=updatePatient patientId={} patientUpdateRequest={}",
                patientId, patientUpdateRequest);
        return patient.toResponse();

    }

    @Transactional
    public PatientResponse changesPatientToInactive(String cpf) {
        Patient patient = this.patientRepository.findByPatientForCpf(cpf)
                .orElseThrow(Message.NOT_FOUND_CRM::asBusinessException);

        patient.setActivePatient(false);
        log.info("method=changesPatientToInactive cpf={}",
                cpf);
        return patient.toResponse();
    }
}

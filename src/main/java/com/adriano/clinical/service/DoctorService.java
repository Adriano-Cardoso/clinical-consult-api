package com.adriano.clinical.service;

import com.adriano.clinical.domain.Doctor;
import com.adriano.clinical.domain.dto.request.DoctorRequest;
import com.adriano.clinical.domain.dto.request.DoctorUpdateRequest;
import com.adriano.clinical.domain.dto.response.DoctorResponse;
import com.adriano.clinical.domain.dto.response.SearchDoctorResponse;
import com.adriano.clinical.exception.Message;
import com.adriano.clinical.repository.DoctorRepository;
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
public class DoctorService {

    private DoctorRepository doctorRepository;

    public DoctorResponse createDoctor(@Valid DoctorRequest doctorRequest) {

        this.doctorRepository.findByName(doctorRequest.getName()).ifPresent(doc -> {
            throw EXISTIS_DOCTOR.asBusinessException();
        });

        Doctor doctor = Doctor.of(doctorRequest);

        this.doctorRepository.save(doctor);

        log.info("method=createDoctor DoctorId={} name={} email={} phone={} crm={} specialty={} isActiveDoctor{} address={}",
                doctor.getDoctorId(), doctor.getName(), doctor.getEmail(), doctor.getPhone(), doctor.getCrm(),
                doctor.getSpecialty(), doctor.isActiveDoctor(), doctor.getAddress());
        return doctor.toResponse();
    }


    public Page<SearchDoctorResponse> searchRegisteredDoctors(int page, int limit, String name) {

        Pageable pageable = PageRequest.of(page, limit);

        log.info("method=searchRegisteredDoctors Name={} limit={}",
                name, limit);
        return doctorRepository.searchRegisteredDoctors(pageable, name);
    }

    @Validated
    @Transactional
    public DoctorResponse updateDoctor(Long doctorId, @Valid DoctorUpdateRequest doctorUpdateRequest) {
        Doctor doctor = this.doctorRepository.findById(doctorId)
                .orElseThrow(Message.NOT_FOUND_ID::asBusinessException);

        doctor.updateDoctor(doctorUpdateRequest);

        log.info("method=updateDoctor doctorId={} doctorUpdateRequest={}",
                doctorId, doctorUpdateRequest);
        return doctor.toResponse();

    }

    @Transactional
    public DoctorResponse changesDoctorToInactive(String crm) {
        Doctor doctor = this.doctorRepository.findByDoctorForCrm(crm)
                .orElseThrow(Message.NOT_FOUND_CRM::asBusinessException);

        doctor.setActiveDoctor(false);
        log.info("method=changesDoctorToInactive crm={}",
                crm);
        return doctor.toResponse();
    }
}

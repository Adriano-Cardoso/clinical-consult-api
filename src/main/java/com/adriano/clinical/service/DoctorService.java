package com.adriano.clinical.service;

import com.adriano.clinical.domain.Doctor;
import com.adriano.clinical.domain.dto.request.DoctorRequest;
import com.adriano.clinical.domain.dto.response.DoctorResponse;
import com.adriano.clinical.domain.dto.response.SearchDoctorResponse;
import com.adriano.clinical.repository.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

import static com.adriano.clinical.exception.Message.EXISTIS_DOCTOR;

@Service
@Validated
@AllArgsConstructor
public class DoctorService {

    private DoctorRepository doctorRepository;

    public DoctorResponse createDoctor(@Valid DoctorRequest doctorRequest) {

        this.doctorRepository.findByName(doctorRequest.getName()).ifPresent(doc -> {
            throw EXISTIS_DOCTOR.asBusinessException();
        });

        Doctor doctor =  Doctor.of(doctorRequest);

        this.doctorRepository.save(doctor);

        return doctor.toResponse();
    }


    public Page<SearchDoctorResponse> searchRegisteredDoctors(int page, int limit, String title){

        Pageable pageable = PageRequest.of(page, limit);

        return doctorRepository.searchRegisteredDoctors(pageable,title);
    }
}

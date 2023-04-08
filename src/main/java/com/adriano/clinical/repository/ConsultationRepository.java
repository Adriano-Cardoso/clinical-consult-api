package com.adriano.clinical.repository;

import com.adriano.clinical.domain.Consultation;
import com.adriano.clinical.domain.Doctor;
import com.adriano.clinical.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

    boolean existsByPatientAndDateBetween(Patient patient, LocalDateTime firstHour, LocalDateTime lastHour);
    boolean existsByDoctorAndDate(Doctor doctor, LocalDateTime dateTime);
}

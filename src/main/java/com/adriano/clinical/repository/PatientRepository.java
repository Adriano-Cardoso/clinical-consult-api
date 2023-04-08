package com.adriano.clinical.repository;

import com.adriano.clinical.domain.Doctor;
import com.adriano.clinical.domain.Patient;
import com.adriano.clinical.domain.dto.response.SearchDoctorResponse;
import com.adriano.clinical.domain.dto.response.SearchPatientResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("select new com.adriano.clinical.domain.dto.response.SearchPatientResponse(" +
            "p.name," +
            "p.email," +
            "p.phone," +
            "p.cpf)" +
            " FROM Patient" +
            " p WHERE (:name is null or p.name like %:name% )" +
            " order by p.name asc")
    Page<SearchPatientResponse> searchRegisteredPatients(Pageable pageable, @Param(value = "name") String name);

    @Query("SELECT p FROM Patient p WHERE p.cpf = :cpf")
    Optional<Patient> findByPatientForCpf(@Param(value = "cpf") String cpf);

    Optional<Patient> findByName(String name);

    @Query("select p.isActivePatient from Patient p where p.patientId = :patientId")
    Boolean findActiveById(@Param("patientId") Long patientId);

}

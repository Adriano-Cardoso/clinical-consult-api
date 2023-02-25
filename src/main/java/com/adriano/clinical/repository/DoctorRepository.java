package com.adriano.clinical.repository;

import com.adriano.clinical.domain.Doctor;
import com.adriano.clinical.domain.dto.response.DoctorResponse;
import com.adriano.clinical.domain.dto.response.SearchDoctorResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByName(String name);

    @Query("select new com.adriano.clinical.domain.dto.response.SearchDoctorResponse(" +
            "d.name," +
            " d.email," +
            "d.phone," +
            " d.crm," +
            " d.specialty)" +
            " FROM Doctor" +
            " d WHERE (:name is null or d.name like %:name% )" +
            " order by d.name asc")
    Page<SearchDoctorResponse> searchRegisteredDoctors(Pageable pageable, @Param(value = "name") String name);

    @Query("SELECT d FROM Doctor d WHERE d.crm = :crm")
    Optional<Doctor> findByDoctorForCrm(@Param(value = "crm") String crm);

}

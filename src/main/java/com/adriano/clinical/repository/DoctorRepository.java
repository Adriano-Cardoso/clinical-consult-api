package com.adriano.clinical.repository;

import com.adriano.clinical.domain.Doctor;
import com.adriano.clinical.domain.dto.response.SearchDoctorResponse;
import com.adriano.clinical.domain.enums.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByName(String name);

    @Query("SELECT d FROM Doctor d where d.name = :name")
    Doctor findByNames(@Param(value = "name") String name);

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


//    @Query("""
//            select d from Doctor d
//            where
//            d.isActiveDoctor = true
//            and
//            d.specialty = :specialty
//            and
//            d.doctorId not in(
//                    select DISTINCT c.doctor.doctorId from Consultation c
//                    where
//                    c.date = :dateTime
//            )
//            order by rand()
//
//            """)

    @Query("""
                select d from Doctor d
                where d.isActiveDoctor = true
                and d.specialty = :specialty
                and d.doctorId not in (
                    select c.doctor.doctorId from Consultation c
                    where c.date = :dateTime
                    group by c.doctor.doctorId
                    having count(c) > 0
                    order by rand()
                )
                order by rand()
            """)
    List<Doctor> chooseRandomDoctorFreeOnDate(@Param(value = "specialty") Specialty specialty, @Param(value = "dateTime") LocalDateTime dateTime);


//    Doctor chooseRandomDoctorFreeOnDate(@Param(value = "specialty") Specialty specialty, @Param(value = "dateTime") LocalDateTime dateTime);

    @Query("select d.isActiveDoctor " +
            "from Doctor d " +
            "where d.doctorId = :doctorId")
    Boolean findActiveById(@Param(value = "doctorId") Long idDoctor);


}

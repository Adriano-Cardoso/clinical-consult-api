package com.adriano.clinical.domain;

import com.adriano.clinical.domain.Doctor;
import com.adriano.clinical.domain.Patient;
import com.adriano.clinical.domain.dto.request.ConsultationRequest;
import com.adriano.clinical.domain.dto.response.CancelConsultationResponse;
import com.adriano.clinical.domain.dto.response.MedicalConsultationResponse;
import com.adriano.clinical.domain.enums.Specialty;
import lombok.*;

import javax.persistence.*;
import javax.print.Doc;
import java.time.LocalDateTime;

@Table(name = "tb_consultation")
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consultation_id")
    private Long consultationId;

    @Column(name = "doctor_id", nullable = false, insertable = false, updatable = false)
    private Long doctorId;

    @Column(name = "patient_id", nullable = false, insertable = false, updatable = false)
    private Long patientId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id")
    private Doctor doctor;

    @Enumerated(EnumType.STRING)
    @Column(name = "specialty")
    private Specialty specialty;

    private LocalDateTime date;

    @Column(name = "cancellation_Reason", length = 100)
    private String cancellationReason;

    public static Consultation of(ConsultationRequest consultationRequest) {
        Patient patient1 = Patient.builder().patientId(consultationRequest.getPatientId())
                .name(consultationRequest.getNamePatient()).build();

        Doctor doctor1 = Doctor.builder().doctorId(consultationRequest.getDoctorId())
                .name(consultationRequest.getNameDoctor()).build();

        return Consultation.builder()
                .patient(patient1)
                .doctor(doctor1)
                .patientId(consultationRequest.getPatientId())
                .doctorId(consultationRequest.getDoctorId())
                .date(consultationRequest.getDateGender())
                .specialty(consultationRequest.getSpecialty()).build();
    }

    public MedicalConsultationResponse toResponse(){
        return MedicalConsultationResponse.builder()
                .consultationId(this.consultationId)
                .doctorId(this.doctorId)
                .patientId(this.patientId)
                .dateTime(this.date)
                .specialty(this.specialty).build();
    }

    public CancelConsultationResponse toCancelConsultationResponse(){
        return CancelConsultationResponse.builder()
                .ConsultationId(this.consultationId)
                .cancellationReason(this.cancellationReason)
                .dateCancel(this.date).build();
    }
}

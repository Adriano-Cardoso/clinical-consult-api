package com.adriano.clinical.domain;

import com.adriano.clinical.domain.Doctor;
import com.adriano.clinical.domain.Patient;
import lombok.*;

import javax.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private LocalDateTime date;
}

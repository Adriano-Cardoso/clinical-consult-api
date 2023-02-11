package com.adriano.clinical.domain;

import com.adriano.clinical.domain.enums.Specialty;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "tb_medico")
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "doctorId")
public class Doctor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Long doctorId;

    @Column(name = "name")
    private String name;;

    @Column(name = "email")
    private String email;

    @Column(name = "cm")
    private String crm;

    @Enumerated(EnumType.STRING)
    @Column(name = "specialty")
    private Specialty specialty;

    @Embedded
    private Address address;
}

package com.adriano.clinical.domain;

import com.adriano.clinical.domain.dto.request.DoctorRequest;
import com.adriano.clinical.domain.dto.response.DoctorResponse;
import com.adriano.clinical.domain.enums.Specialty;
import lombok.*;

import javax.persistence.*;

@Table(name = "tb_medicos")
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "doctorId")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Long doctorId;

    @Column(name = "name")
    private String name;;

    @Column(name = "email")
    private String email;

    @Column(name = "crm")
    private String crm;

    @Enumerated(EnumType.STRING)
    @Column(name = "specialty")
    private Specialty specialty;

    @Embedded
    private Address address;

    public static Doctor of(DoctorRequest doctorRequest) {
        Address address = Address.builder()
                .publicPlace(doctorRequest.getAddress().getPublicPlace())
                .number(doctorRequest.getAddress().getNumber())
                .complement(doctorRequest.getAddress().getComplement())
                .district(doctorRequest.getAddress().getDistrict())
                .city(doctorRequest.getAddress().getCity())
                .uf(doctorRequest.getAddress().getUf())
                .cep(doctorRequest.getAddress().getCep()).build();

        return Doctor.builder()
                .name(doctorRequest.getName())
                .email(doctorRequest.getEmail())
                .crm(doctorRequest.getCrm())
                .specialty(doctorRequest.getSpecialty())
                .address(address).build();
    }

    public DoctorResponse toResponse() {
        return DoctorResponse.builder()
                .doctorId(this.doctorId)
                .name(this.name)
                .email(this.email)
                .crm(this.crm)
                .specialty(this.specialty)
                .publicPlace(this.address.getPublicPlace())
                .number(this.address.getNumber())
                .complement(this.address.getComplement())
                .district(this.address.getDistrict())
                .city(this.address.getCity())
                .uf(this.address.getUf())
                .cep(this.address.getCep()).build();
    }
}

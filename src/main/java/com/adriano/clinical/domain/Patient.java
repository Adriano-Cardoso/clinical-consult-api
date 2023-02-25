package com.adriano.clinical.domain;

import com.adriano.clinical.domain.dto.request.DoctorRequest;
import com.adriano.clinical.domain.dto.request.PatientRequest;
import com.adriano.clinical.domain.dto.request.PatientUpdateRequest;
import com.adriano.clinical.domain.dto.response.PatientResponse;
import lombok.*;

import javax.persistence.*;

@Table(name = "tb_patient")
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "patientId")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Long patientId;

    @Column(name = "name")
    private String name;;

    @Column(name = "email")
    private String email;

    @Column(name = "cpf")
    private String cpf;

    private String phone;

    @Column(name = "active_patient")
    private boolean isActivePatient;

    @Embedded
    private Address address;

    public static Patient of(PatientRequest patientRequest) {
        Address address = Address.builder()
                .publicPlace(patientRequest.getAddress().getPublicPlace())
                .number(patientRequest.getAddress().getNumber())
                .complement(patientRequest.getAddress().getComplement())
                .district(patientRequest.getAddress().getDistrict())
                .city(patientRequest.getAddress().getCity())
                .uf(patientRequest.getAddress().getUf())
                .cep(patientRequest.getAddress().getCep()).build();

        return Patient.builder()
                .name(patientRequest.getName())
                .email(patientRequest.getEmail())
                .phone(patientRequest.getPhone())
                .cpf(patientRequest.getCpf())
                .isActivePatient(patientRequest.getIsActivePatient())
                .address(address).build();
    }

    public PatientResponse toResponse() {
        return PatientResponse.builder()
                .patientId(this.patientId)
                .name(this.name)
                .email(this.email)
                .cpf(this.cpf)
                .phone(this.phone)
                .isActivePatient(this.isActivePatient)
                .publicPlace(this.address.getPublicPlace())
                .number(this.address.getNumber())
                .complement(this.address.getComplement())
                .district(this.address.getDistrict())
                .city(this.address.getCity())
                .uf(this.address.getUf())
                .cep(this.address.getCep()).build();
    }

    public void updatePatient(PatientUpdateRequest patientUpdateRequest){
        this.name = patientUpdateRequest.getName();
        this.phone = patientUpdateRequest.getPhone();
        this.isActivePatient = patientUpdateRequest.getIsActiveDoctor();
        this.address.setCep(patientUpdateRequest.getAddress().getCep());
        this.address.setUf(patientUpdateRequest.getAddress().getUf());
        this.address.setCity(patientUpdateRequest.getAddress().getCity());
        this.address.setComplement(patientUpdateRequest.getAddress().getComplement());
        this.address.setDistrict(patientUpdateRequest.getAddress().getDistrict());
        this.address.setNumber(patientUpdateRequest.getAddress().getNumber());
        this.address.setPublicPlace(patientUpdateRequest.getAddress().getPublicPlace());

    }

    public void setDoctorInactive(DoctorRequest doctorRequest){
        this.isActivePatient = doctorRequest.getIsActiveDoctor();
    }
}

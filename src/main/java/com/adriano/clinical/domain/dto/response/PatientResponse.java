package com.adriano.clinical.domain.dto.response;

import com.adriano.clinical.domain.enums.Specialty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponse {

    private Long patientId;

    private String name;

    private String email;

    private String cpf;

    private String phone;

    private boolean isActivePatient;

    private AddressResponse address;

    @Builder
    public PatientResponse(Long patientId, String name, String email, String cpf, String phone,
                           boolean isActivePatient, String publicPlace, String number,
                           String complement, String district, String city, String uf, String cep) {
        this.patientId = patientId;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.phone = phone;
        this.isActivePatient = isActivePatient;
        this.address = new AddressResponse(publicPlace, number, complement, district, city, uf, cep);
    }

}


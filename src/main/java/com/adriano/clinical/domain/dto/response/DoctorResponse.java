package com.adriano.clinical.domain.dto.response;

import com.adriano.clinical.domain.enums.Specialty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorResponse {

    private Long doctorId;

    private String name;

    private String email;

    private String crm;

    private Specialty specialty;


    private AddressResponse address;

    @Builder
    public DoctorResponse(Long doctorId, String name, String email, String crm,
                          Specialty specialty, String publicPlace, String number,
                          String complement, String district, String city, String uf, String cep) {
        this.doctorId = doctorId;
        this.name = name;
        this.email = email;
        this.crm = crm;
        this.specialty = specialty;
        this.address = new AddressResponse(publicPlace, number, complement, district, city, uf, cep);
    }

}


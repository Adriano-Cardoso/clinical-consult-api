package com.adriano.clinical.domain.dto.response;

import com.adriano.clinical.domain.enums.Specialty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchPatientResponse {

    private String name;

    private String email;

    private String phone;

    private String cpf;

}

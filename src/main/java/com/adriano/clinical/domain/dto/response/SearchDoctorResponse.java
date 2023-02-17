package com.adriano.clinical.domain.dto.response;

import com.adriano.clinical.domain.enums.Specialty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchDoctorResponse {

    private String name;

    private String email;

    private String crm;

    private Specialty specialty;


}

package com.adriano.clinical.domain.dto.response;

import com.adriano.clinical.domain.dto.request.AddressRequest;
import com.adriano.clinical.domain.enums.Specialty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorResponse {

    private String name;

    private String email;

    private String crm;

    private Specialty specialty;

    private AddressRequest address;
}


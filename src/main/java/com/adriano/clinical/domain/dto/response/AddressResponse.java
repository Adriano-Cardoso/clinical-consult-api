package com.adriano.clinical.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {

    private String publicPlace;

    private String number;

    private String complement;

    private String district;

    private String city;

    private String uf;

    private String cep;
}

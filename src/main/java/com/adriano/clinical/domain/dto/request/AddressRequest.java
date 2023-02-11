package com.adriano.clinical.domain.dto.request;

import jakarta.persistence.Column;
import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {

    @NotNull(message = "O valor do campo 'publicPlace' é obrigatório no corpo da requisição")
    private String publicPlace;

    @NotNull(message = "O valor do campo 'district' é obrigatório no corpo da requisição")
    private String district;

    @NotNull(message = "O valor do campo 'city' é obrigatório no corpo da requisição")
    private String city;

    @NotNull(message = "O valor do campo 'cep' é obrigatório no corpo da requisição")
    private String cep;

    private String complement;

    private String number;

    private String uf;
}

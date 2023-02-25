package com.adriano.clinical.domain.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressUpdateRequest {

    @ApiModelProperty(position = 1, value = "Cep do endereço", name = "cep", dataType = "String", example = "00000000")
    private String cep;

    @ApiModelProperty(position = 2, value = "Complemento do endereço", name = "complement", dataType = "String", example = "Ao Lado do mercado amarelo")
    private String complement;

    @ApiModelProperty(position = 3, value = "Número do endereço", name = "number", dataType = "String", example = "116")
    private String number;

    @ApiModelProperty(position = 1, value = "Logradouro do Médico", name = "name", dataType = "String", example = "Rua da riqueza")
    private String publicPlace;

    @ApiModelProperty(position = 1, value = "Bairro do endereço", name = "district", dataType = "String", example = "Boa viagem")
    private String district;

    @ApiModelProperty(position = 1, value = "Cidade do endereço", name = "city", dataType = "String", example = "Recife")
    private String city;

    @ApiModelProperty(position = 1, value = "Uf do endereço", name = "uf", dataType = "String", example = "PE")
    private String uf;
}

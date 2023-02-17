package com.adriano.clinical.domain.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {

    @NotNull(message = "O valor do campo 'cep' é obrigatório no corpo da requisição")
    @ApiModelProperty(position = 1, value = "Cep do endereço", name = "cep", dataType = "String", example = "00000000")
    private String cep;

    @ApiModelProperty(position = 2, value = "Complemento do endereço", name = "complement", dataType = "String", example = "Ao Lado do mercado amarelo")
    private String complement;

    @ApiModelProperty(position = 3, value = "Número do endereço", name = "number", dataType = "String", example = "116")
    private String number;

    @NotNull(message = "O valor do campo 'publicPlace' é obrigatório no corpo da requisição")
    @ApiModelProperty(position = 1, value = "Logradouro do Médico", name = "name", dataType = "String", example = "Rua da riqueza")
    private String publicPlace;

    @NotNull(message = "O valor do campo 'district' é obrigatório no corpo da requisição")
    @ApiModelProperty(position = 1, value = "Bairro do endereço", name = "district", dataType = "String", example = "Boa viagem")
    private String district;

    @NotNull(message = "O valor do campo 'city' é obrigatório no corpo da requisição")
    @ApiModelProperty(position = 1, value = "Cidade do endereço", name = "city", dataType = "String", example = "Recife")
    private String city;

    @ApiModelProperty(position = 1, value = "Uf do endereço", name = "uf", dataType = "String", example = "PE")
    private String uf;



}

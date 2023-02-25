package com.adriano.clinical.domain.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorUpdateRequest {

    @ApiModelProperty(position = 1, value = "Nome do Médico", name = "name", dataType = "String", example = "Odin Monte Goulart")
    private String name;

    @ApiModelProperty(position = 1, value = "Nome do telefone", name = "phone", dataType = "String", example = "81000000000")
    private String phone;

    private Boolean isActiveDoctor;

    @ApiModelProperty(position = 3, value = "Endereço do Médico", name = "address", dataType = "String")
    private AddressUpdateRequest address;
}

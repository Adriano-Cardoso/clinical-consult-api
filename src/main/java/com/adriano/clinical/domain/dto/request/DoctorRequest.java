package com.adriano.clinical.domain.dto.request;

import com.adriano.clinical.domain.enums.Specialty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorRequest {

    @NotNull(message = "O valor do campo 'name' é obrigatório no corpo da requisição")
    @ApiModelProperty(position = 1, value = "Nome do Médico", name = "name", dataType = "String", example = "Odin Monte Goulart")
    private String name;;

    @NotNull(message = "O valor do campo 'email' é obrigatório no corpo da requisição")
    @NotEmpty(message = "O valor do campo 'email' é obrigatório no corpo da requisição")
    @Email
    @ApiModelProperty(position = 2, value = "Email do Médico", name = "email", dataType = "String", example = "odin@email.com")
    private String email;

    @NotNull(message = "O valor do campo 'phone' é obrigatório no corpo da requisição")
    @NotEmpty(message = "O valor do campo 'phone' é obrigatório no corpo da requisição")
    @ApiModelProperty(position = 3, value = "Email do telefone", name = "phone", dataType = "String", example = "8100000000")
    private String phone;

    @NotNull(message = "O valor do campo 'crm' é obrigatório no corpo da requisição")
    @Pattern(regexp = "\\d{4,6}")
    @ApiModelProperty(position = 4, value = "Crm do Médico", name = "crm", dataType = "String", example = "123456")
    private String crm;

    @NotNull(message = "O valor do campo 'specialty' é obrigatório no corpo da requisição")
    @ApiModelProperty(position = 5, value = "Especialidade do Médico", name = "specialty", dataType = "String", example = "ORTHOPEDICS")
    private Specialty specialty;

    @NotNull(message = "O valor do campo 'address' é obrigatório no corpo da requisição")
    @ApiModelProperty(position = 6, value = "Campo que verifica se o Médico está Ativo", name = "isActiveDoctor", dataType = "boolean")
    private Boolean isActiveDoctor;

    @NotNull(message = "O valor do campo 'address' é obrigatório no corpo da requisição")
    @ApiModelProperty(position = 7, value = "Endereço do Médico", name = "address", dataType = "String")
    private AddressRequest address;

}

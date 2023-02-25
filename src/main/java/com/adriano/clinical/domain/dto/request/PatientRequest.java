package com.adriano.clinical.domain.dto.request;

import com.adriano.clinical.domain.enums.Specialty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PatientRequest {

    @NotNull(message = "O valor do campo 'name' é obrigatório no corpo da requisição")
    @ApiModelProperty(position = 1, value = "Nome do paciente", name = "name", dataType = "String", example = "Odin Monte Goulart")
    private String name;;

    @NotNull(message = "O valor do campo 'email' é obrigatório no corpo da requisição")
    @NotEmpty(message = "O valor do campo 'email' é obrigatório no corpo da requisição")
    @Email
    @ApiModelProperty(position = 2, value = "Email do paciente", name = "email", dataType = "String", example = "odin@email.com")
    private String email;

    @NotNull(message = "O valor do campo 'cpf' é obrigatório no corpo da requisição")
    @NotEmpty(message = "O valor do campo 'cpf' é obrigatório no corpo da requisição")
    @CPF
    @ApiModelProperty(position = 3, value = "cpf do Paciente", name = "cpf", dataType = "String", example = "000.000.000-00")
    private String cpf;

    @NotNull(message = "O valor do campo 'phone' é obrigatório no corpo da requisição")
    @NotEmpty(message = "O valor do campo 'phone' é obrigatório no corpo da requisição")
    @ApiModelProperty(position = 4, value = "Email do telefone", name = "phone", dataType = "String", example = "8100000000")
    private String phone;

    @NotNull(message = "O valor do campo 'paciente' é obrigatório no corpo da requisição")
    @ApiModelProperty(position = 6, value = "Campo que verifica se o Paciente está Ativo", name = "isActiveDoctor", dataType = "boolean")
    private Boolean isActivePatient;

    @NotNull(message = "O valor do campo 'address' é obrigatório no corpo da requisição")
    @ApiModelProperty(position = 7, value = "Endereço do Médico", name = "address", dataType = "String")
    private AddressRequest address;

}

package com.adriano.clinical.domain.dto.request;

import com.adriano.clinical.domain.enums.Specialty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorRequest {

    @NotNull(message = "O valor do campo 'name' é obrigatório no corpo da requisição")
    private String name;;

    @NotNull(message = "O valor do campo 'email' é obrigatório no corpo da requisição")
    @Email
    private String email;

    @NotNull(message = "O valor do campo 'crm' é obrigatório no corpo da requisição")
    @Pattern(regexp = "\\d{4,6}")
    private String crm;

    @NotNull(message = "O valor do campo 'specialty' é obrigatório no corpo da requisição")
    private Specialty specialty;

    @NotNull(message = "O valor do campo 'address' é obrigatório no corpo da requisição")
    private AddressRequest address;
}

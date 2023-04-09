package com.adriano.clinical.domain.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationCancelRequest {

    @ApiModelProperty(position = 1, value = "Data do cancelamento daa consulta", name = "dateCancel", dataType = "LocalDateTime", example = "00000000")
    private LocalDateTime dateCancel;

    @ApiModelProperty(position = 2, value = "Motivo do cancelamento daa consulta", name = "cancellationReason", dataType = "String", example = "Médico cancelou")
    private String cancellationReason;
}

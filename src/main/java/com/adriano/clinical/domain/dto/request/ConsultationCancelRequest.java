package com.adriano.clinical.domain.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationCancelRequest {

    private LocalDateTime dateCancel;

    private String cancellationReason;
}

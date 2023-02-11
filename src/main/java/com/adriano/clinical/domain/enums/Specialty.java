package com.adriano.clinical.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Specialty {
    ORTHOPEDICS(0, "Ortopedia"),
    CARDIOLOGY(1, "Cardiologia"),
    GYNECOLOGY(2, "Ginecologia"),
    DERMATOLOGY(4, "Dermatologia");

    @Getter
    private final Integer code;
    @Getter
    private final String description;
}

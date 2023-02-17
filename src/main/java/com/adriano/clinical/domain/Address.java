package com.adriano.clinical.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Column(name = "public_place")
    private String publicPlace;

    @Column(name = "number")
    private String number;

    @Column(name = "complement")
    private String complement;

    @Column(name = "district")
    private String district;

    @Column(name = "city")
    private String city;

    @Column(name = "uf")
    private String uf;

    @Column(name = "cep")
    private String cep;
}

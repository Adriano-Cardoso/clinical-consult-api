package com.adriano.clinical.feature;

import com.adriano.clinical.domain.Address;
import com.adriano.clinical.domain.Doctor;
import com.adriano.clinical.domain.dto.request.AddressRequest;
import com.adriano.clinical.domain.dto.request.AddressUpdateRequest;
import com.adriano.clinical.domain.dto.request.DoctorRequest;
import com.adriano.clinical.domain.dto.request.DoctorUpdateRequest;
import com.adriano.clinical.domain.dto.response.AddressResponse;
import com.adriano.clinical.domain.dto.response.DoctorResponse;
import com.adriano.clinical.domain.dto.response.SearchDoctorResponse;
import com.adriano.clinical.domain.enums.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

public class DoctorScenarioFactory {

    public static final Doctor DOCTOR = loadCreateDoctor() ;
    public static final DoctorRequest DOCTOR_REQUEST = loadDcotorRequest();
    public static final Page<SearchDoctorResponse> LIST_DOCTOR = loadDoctoList();
    public static final DoctorUpdateRequest DOCTOR_UPDATE = loadUpdateDoctor() ;

    private static DoctorUpdateRequest loadUpdateDoctor() {

        AddressUpdateRequest addressUpdateRequest = AddressUpdateRequest.builder()
                .publicPlace("rua 1")
                .number("00")
                .complement("ao lado da rua dois")
                .district("Boa viagem")
                .city("Recife")
                .uf("PE")
                .cep("00000000").build();

        return DoctorUpdateRequest.builder().name("Meteo").phone("81000000000").isActiveDoctor(true).address(addressUpdateRequest).build();
    }

    private static Page<SearchDoctorResponse> loadDoctoList() {

        PageRequest pageRequest = PageRequest.of(0, 10);

        SearchDoctorResponse searchDoctorResponse = SearchDoctorResponse.builder()
                .name("Teste_01")
                .email("teste@email.com")
                .phone("81000000000")
                .crm("00000")
                .specialty(Specialty.CARDIOLOGY).build();

        List<SearchDoctorResponse> searchDoctorResponses = new ArrayList<>();

        searchDoctorResponses.add(searchDoctorResponse);

        return new PageImpl<>(searchDoctorResponses, pageRequest, 10);
    }

    private static DoctorRequest loadDcotorRequest() {
        AddressRequest addressRequest = AddressRequest.builder()
                .publicPlace("rua 1")
                .number("00")
                .complement("ao lado da rua dois")
                .district("Boa viagem")
                .city("Recife")
                .uf("PE")
                .cep("00000000").build();

        return DoctorRequest.builder()
                .name("Teste_01")
                .email("teste@email.com")
                .phone("81000000000")
                .crm("00000")
                .specialty(Specialty.CARDIOLOGY)
                .isActiveDoctor(true)
                .address(addressRequest).build();
    }

    private static Doctor loadCreateDoctor() {

        Address address = Address.builder()
                .publicPlace("rua 1")
                .number("00")
                .complement("ao lado da rua dois")
                .district("Boa viagem")
                .city("Recife")
                .uf("PE")
                .cep("00000000").build();

        return Doctor.builder()
                .doctorId(1L)
                .name("Teste_01")
                .email("teste@email.com")
                .phone("81000000000")
                .crm("00000")
                .specialty(Specialty.CARDIOLOGY)
                .isActiveDoctor(true)
                .address(address).build();
    }
}

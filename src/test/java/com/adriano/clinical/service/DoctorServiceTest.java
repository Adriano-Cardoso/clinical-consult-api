package com.adriano.clinical.service;

import com.adriano.clinical.domain.Doctor;
import com.adriano.clinical.domain.dto.request.DoctorUpdateRequest;
import com.adriano.clinical.domain.dto.response.DoctorResponse;
import com.adriano.clinical.domain.dto.response.SearchDoctorResponse;
import com.adriano.clinical.exception.BusinessException;
import com.adriano.clinical.feature.DoctorScenarioFactory;
import com.adriano.clinical.repository.DoctorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {

    @InjectMocks
    private DoctorService doctorService;

    @Mock
    private DoctorRepository doctorRepository;

    @Test
    @DisplayName("Create doctor given name is valid")
    void createDoctor_WhenValidationNameIsValid_ExpectedOk() {

        when(this.doctorRepository.findByName("Teste_01")).thenReturn(Optional.empty());

        when(this.doctorRepository.save(any())).thenReturn(DoctorScenarioFactory.DOCTOR);

        DoctorResponse doctorResponse = this.doctorService.createDoctor(DoctorScenarioFactory.DOCTOR_REQUEST);

        assertNotNull(doctorResponse);

        verify(doctorRepository).findByName("Teste_01");

        verify(doctorRepository).save(any());
    }

    @Test
    @DisplayName("Error Creating doctor name invalid name")
    void createDoctor_WhenErrorCreatingDoctorInvalidName_ExpectedException() {

        when(this.doctorRepository.findByName("Teste_01")).thenReturn(Optional.of(DoctorScenarioFactory.DOCTOR));

        assertThrows(BusinessException.class, () -> doctorService.createDoctor(DoctorScenarioFactory.DOCTOR_REQUEST));
    }

    @Test
    @DisplayName("Search doctor page for name doctor")
    void searchRegisteredDoctors_WhenSelectedName_ExpectedOK() {

        when(this.doctorRepository.searchRegisteredDoctors(any(Pageable.class), anyString())).thenReturn(DoctorScenarioFactory.LIST_DOCTOR);

        Page<SearchDoctorResponse> searchDoctorResponsePage = this.doctorService.searchRegisteredDoctors(0, 10, "Teste_01");

        assertNotNull(searchDoctorResponsePage);

        assertEquals(DoctorScenarioFactory.LIST_DOCTOR, searchDoctorResponsePage);

        verify(doctorRepository).searchRegisteredDoctors(any(), anyString());

    }

    @Test
    @DisplayName("update parameters for id is valid")
    void updateDoctor_WhenExistId_ExpectedUpdate(){

        when(this.doctorRepository.findById(anyLong())).thenReturn(Optional.of(DoctorScenarioFactory.DOCTOR));

        DoctorResponse doctorResponse = this.doctorService.updateDoctor(1L, DoctorScenarioFactory.DOCTOR_UPDATE);

        assertNotNull(doctorResponse);
    }

    @Test
    @DisplayName("update parameters for id is not valid")
    void updateDoctor_WhenNotExistId_ExpectedNotUpdate(){

        when(this.doctorRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(BusinessException.class, () -> this.doctorService.updateDoctor(2L, DoctorScenarioFactory.DOCTOR_UPDATE));

    }

    @Test
    void changesDoctorToInactive_WhenDoctorIsActive_ExpectedInactiveOK(){
        when(this.doctorRepository.findByDoctorForCrm("00000")).thenReturn(Optional.of(DoctorScenarioFactory.DOCTOR));

        this.doctorService.changesDoctorToInactive("00000");
    }

    @Test
    void changesDoctorToInactive_WhenDoctorCrmIsEmpty_ExpectedInactiveOK(){
        when(this.doctorRepository.findByDoctorForCrm("99999")).thenReturn(Optional.empty());

        assertThrows(BusinessException.class, () -> this.doctorService.changesDoctorToInactive("99999"));

    }
}

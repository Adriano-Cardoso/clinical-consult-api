package com.adriano.clinical.controller;


import com.adriano.clinical.domain.dto.request.DoctorRequest;
import com.adriano.clinical.domain.dto.request.DoctorUpdateRequest;
import com.adriano.clinical.domain.dto.request.PatientRequest;
import com.adriano.clinical.domain.dto.request.PatientUpdateRequest;
import com.adriano.clinical.domain.dto.response.DoctorResponse;
import com.adriano.clinical.domain.dto.response.PatientResponse;
import com.adriano.clinical.domain.dto.response.SearchDoctorResponse;
import com.adriano.clinical.domain.dto.response.SearchPatientResponse;
import com.adriano.clinical.service.PatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "Patient Endpoint", description = "Endpoints Patient", tags = {"Patient Endpoint"})
@RestController
@AllArgsConstructor
@RequestMapping("/patient")
public class PatientController {

    private PatientService patientService;

    @ApiOperation(value = "Cria um novo paciente")
    @PostMapping
    public ResponseEntity<PatientResponse> createPatient(@RequestBody PatientRequest patientRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.patientService.createPatient(patientRequest));
    }

    @ApiOperation(value = "Busca por pacientes paginada e ordenada pelo nome")
    @GetMapping
    public ResponseEntity<Page<SearchPatientResponse>> searchRegisteredPatients(
            @RequestParam(required = false, defaultValue = "0", name = "page") int page,
            @RequestParam(required = false, defaultValue = "10", name = "limit") int limit,
            @RequestParam(required = false, name = "name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(this.patientService.searchRegisteredPatients(page, limit, name));
    }

    @ApiOperation(value = "Atualiza dados do paciente")
    @PutMapping("/{patientId}")
    public ResponseEntity<PatientResponse> updatePatient(@PathVariable("patientId") Long patientId,
                                                       @RequestBody PatientUpdateRequest patientUpdateRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(this.patientService.updatePatient(patientId, patientUpdateRequest));
    }

    @ApiOperation(value = "Endpoint responsável por mudar o paciente para inativo e matem os dados na base")
    @PatchMapping("/{cpf}")
    public ResponseEntity<PatientResponse> changesPatientToInactive(@PathVariable("cpf") String cpf){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(this.patientService.changesPatientToInactive(cpf));
    }
}

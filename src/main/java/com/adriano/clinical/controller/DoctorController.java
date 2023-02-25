package com.adriano.clinical.controller;


import com.adriano.clinical.domain.dto.request.DoctorRequest;
import com.adriano.clinical.domain.dto.request.DoctorUpdateRequest;
import com.adriano.clinical.domain.dto.response.DoctorResponse;
import com.adriano.clinical.domain.dto.response.SearchDoctorResponse;
import com.adriano.clinical.service.DoctorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "Doctor Endpoint", description = "Endpoints Doctor", tags = {"Doctor Endpoint"})
@RestController
@AllArgsConstructor
@RequestMapping("/doctor")
public class DoctorController {

    private DoctorService doctorService;

    @ApiOperation(value = "Cria um novo médico")
    @PostMapping
    public ResponseEntity<DoctorResponse> createDoctor(@RequestBody DoctorRequest doctorRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.doctorService.createDoctor(doctorRequest));
    }

    @ApiOperation(value = "Busca por médicos paginada e ordenada pelo nome")
    @GetMapping
    public ResponseEntity<Page<SearchDoctorResponse>> listar(
            @RequestParam(required = false, defaultValue = "0", name = "page") int page,
            @RequestParam(required = false, defaultValue = "10", name = "limit") int limit,
            @RequestParam(required = false, name = "name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(this.doctorService.searchRegisteredDoctors(page, limit, name));
    }

    @ApiOperation(value = "Atualiza dados do médico")
    @PutMapping("/{doctorId}")
    public ResponseEntity<DoctorResponse> updateDoctor(@PathVariable("doctorId") Long doctorId,
                                                       @RequestBody DoctorUpdateRequest doctorUpdateRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(this.doctorService.updateDoctor(doctorId, doctorUpdateRequest));
    }

    @ApiOperation(value = "Endpoint responsável por mudar o médico para inativo e matem os dados na base")
    @PatchMapping("/{crm}")
    public ResponseEntity<DoctorResponse> changesDoctorToInactive(@PathVariable("crm") String crm){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(this.doctorService.changesDoctorToInactive(crm));
    }
}

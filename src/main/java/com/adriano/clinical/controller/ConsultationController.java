package com.adriano.clinical.controller;

import com.adriano.clinical.domain.dto.request.ConsultationCancelRequest;
import com.adriano.clinical.domain.dto.request.ConsultationRequest;
import com.adriano.clinical.domain.dto.response.CancelConsultationResponse;
import com.adriano.clinical.domain.dto.response.MedicalConsultationResponse;
import com.adriano.clinical.service.ConsultationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "Consultation Endpoint", description = "Endpoints Consultation", tags = {"Consultation Endpoint"})
@RestController
@AllArgsConstructor
@RequestMapping("/consultation")
public class ConsultationController {

    private ConsultationService consultationService;

    @ApiOperation(value = "Endpoint responsável por agendar consulta")
    @PostMapping
    public ResponseEntity<MedicalConsultationResponse> scheduleConsultation(@RequestBody ConsultationRequest consultationRequest){
        return ResponseEntity.status(HttpStatus.OK).body(this.consultationService.scheduleConsultation(consultationRequest));
    }

    @ApiOperation(value = "Endpoint responsável por cancelar consultas")
    @PutMapping("/{consultationId}/cancel")
    public ResponseEntity<CancelConsultationResponse> cancellConsultation(@PathVariable Long consultationId, @RequestBody ConsultationCancelRequest consultationCancelRequest){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(this.consultationService.cancelConsultation(consultationId,consultationCancelRequest));
    }
}

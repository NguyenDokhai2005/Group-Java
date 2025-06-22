package com.example.DICOM.Controller;

import com.example.DICOM.DTO.DiagnosisResultDTO;
import com.example.DICOM.Mapper.DiagnosisResultMapper;
import com.example.DICOM.Service.DiagnosisResultService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/diagnosis-results")
public class DiagnosisResultController {

    private final DiagnosisResultService service;
    private final DiagnosisResultMapper mapper;

    public DiagnosisResultController(DiagnosisResultService service, DiagnosisResultMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/getAll")
    public List<DiagnosisResultDTO> getAll() {
        return service.getAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/image/{imageId}")
    public List<DiagnosisResultDTO> getByImageId(@PathVariable Long imageId) {
        return service.getByImageId(imageId)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/patient/{patientId}")
    public List<DiagnosisResultDTO> getByPatientId(@PathVariable Long patientId) {
        return service.getByPatientId(patientId)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public DiagnosisResultDTO create(@RequestBody DiagnosisResultDTO dto) {
        var saved = service.save(mapper.toEntity(dto));
        return mapper.toDTO(saved);
    }
}

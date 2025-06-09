package com.example.DICOM.Controller;

import com.example.DICOM.DTO.DiagnosisResultDTO;
import com.example.DICOM.Service.DiagnosisResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diagnosis")
public class DiagnosisResultController {

    @Autowired
    private DiagnosisResultService diagnosisResultService;

    @PostMapping
    public boolean createDiagnosisResult(@RequestBody DiagnosisResultDTO dto) {
        return diagnosisResultService.createDiagnosisResult(dto);
    }

    @GetMapping("/user/{userId}")
    public List<DiagnosisResultDTO> getByUser(@PathVariable Long userId) {
        return diagnosisResultService.getByUser(userId);
    }

    @GetMapping("/image/{imageId}")
    public List<DiagnosisResultDTO> getByImage(@PathVariable Long imageId) {
        return diagnosisResultService.getByImage(imageId);
    }
}

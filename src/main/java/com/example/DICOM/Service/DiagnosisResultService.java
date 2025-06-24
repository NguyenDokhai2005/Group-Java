package com.example.DICOM.Service;

import com.example.DICOM.Entity.DiagnosisResult;
import com.example.DICOM.Repository.DiagnosisResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosisResultService {

    private final DiagnosisResultRepository repository;

    public DiagnosisResultService(DiagnosisResultRepository repository) {
        this.repository = repository;
    }

    public List<DiagnosisResult> getByImageId(Long imageId) {
        return repository.findByImageId(imageId);
    }

    public List<DiagnosisResult> getByPatientId(Long patientId) {
        return repository.findByPatientId(patientId);
    }

    public DiagnosisResult save(DiagnosisResult result) {
        return repository.save(result);
    }

    public List<DiagnosisResult> getAll() {
        return repository.findAll();
    }
}


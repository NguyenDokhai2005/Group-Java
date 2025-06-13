package com.example.DICOM.Mapper;

import com.example.DICOM.DTO.DiagnosisResultDTO;
import com.example.DICOM.Entity.DiagnosisResult;

import org.springframework.stereotype.Component;

@Component
public class DiagnosisResultMapper {

    public DiagnosisResultDTO toDTO(DiagnosisResult entity) {
        DiagnosisResultDTO dto = new DiagnosisResultDTO();
        dto.setId(entity.getId());
        dto.setPatientId(entity.getPatientId());
        dto.setImageId(entity.getImageId());
        dto.setDiagnosis(entity.getDiagnosis());
        dto.setTimestamp(entity.getTimestamp());
        return dto;
    }

    public DiagnosisResult toEntity(DiagnosisResultDTO dto) {
        DiagnosisResult entity = new DiagnosisResult();
        entity.setId(dto.getId());
        entity.setPatientId(dto.getPatientId());
        entity.setImageId(dto.getImageId());
        entity.setDiagnosis(dto.getDiagnosis());
        entity.setTimestamp(dto.getTimestamp());
        return entity;
    }
}
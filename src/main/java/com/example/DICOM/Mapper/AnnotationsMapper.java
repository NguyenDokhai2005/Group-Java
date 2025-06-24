package com.example.DICOM.Mapper;

import com.example.DICOM.DTO.AnnotationsDTO;
import com.example.DICOM.Entity.Annotations;

public class AnnotationsMapper {
    public static AnnotationsDTO toDTO(Annotations entity) {
        AnnotationsDTO dto = new AnnotationsDTO();
        dto.setId(entity.getAnnotationId());
        dto.setImageId(entity.getDicomImage().getId());
        dto.setUserId(entity.getUser().getUserId());
        dto.setUsername(entity.getUser().getUsername());
        dto.setAnnotationText(entity.getAnnotationText());
        dto.setNote(entity.getNote());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }
}
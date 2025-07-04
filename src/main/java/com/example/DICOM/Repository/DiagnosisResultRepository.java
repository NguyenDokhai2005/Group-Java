package com.example.DICOM.Repository;

import com.example.DICOM.Entity.DiagnosisResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiagnosisResultRepository extends JpaRepository<DiagnosisResult, Long> {
    List<DiagnosisResult> findByImageId(Long imageId);
    List<DiagnosisResult> findByPatientId(Long patientId);
}

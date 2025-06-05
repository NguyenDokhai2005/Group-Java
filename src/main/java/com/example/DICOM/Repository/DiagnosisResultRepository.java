package com.example.DICOM.Repository;

import com.example.DICOM.Entity.DiagnosisResult;
import com.example.DICOM.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiagnosisResultRepository extends JpaRepository<DiagnosisResult, Long> {

    List<DiagnosisResult> findByAnalyzedBy(User user);

    List<DiagnosisResult> findByConfidenceScoreGreaterThan(Double score);

    List<DiagnosisResult> findByDiagnosisContainingIgnoreCase(String keyword);
}

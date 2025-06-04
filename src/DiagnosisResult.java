package com.example.DICOM.model;

import jakarta.persistence.*;

@Entity
public class DiagnosisResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patientId;
    private String diagnosis;
    private String confidence;

    public DiagnosisResult() {
    }

    public DiagnosisResult(String patientId, String diagnosis, String confidence) {
        this.patientId = patientId;
        this.diagnosis = diagnosis;
        this.confidence = confidence;
    }

    public Long getId() {
        return id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getConfidence() {
        return confidence;
    }

    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }
}

    public void setAnalysisDate(LocalDateTime analysisDate) {
        this.analysisDate = analysisDate;
    }
}

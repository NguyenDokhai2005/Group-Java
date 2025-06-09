package com.example.DICOM.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class DiagnosisResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "img_id")
    private DicomImage image;

    @ManyToOne
    @JoinColumn(name = "analyzed_by")
    private User analyzedBy;

    private String diagnosis;
    private Double confidenceScore;
    private LocalDateTime analysisDate;

    public DiagnosisResult() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DicomImage getImage() {
        return image;
    }

    public void setImage(DicomImage image) {
        this.image = image;
    }

    public User getAnalyzedBy() {
        return analyzedBy;
    }

    public void setAnalyzedBy(User analyzedBy) {
        this.analyzedBy = analyzedBy;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Double getConfidenceScore() {
        return confidenceScore;
    }

    public void setConfidenceScore(Double confidenceScore) {
        this.confidenceScore = confidenceScore;
    }

    public LocalDateTime getAnalysisDate() {
        return analysisDate;
    }

    public void setAnalysisDate(LocalDateTime analysisDate) {
        this.analysisDate = analysisDate;
    }
}

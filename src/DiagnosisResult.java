package com.example.DICOM.Entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "diagnosis_results")
public class DiagnosisResult {

    @Id
    @Column(name = "result_id")
    private Integer resultId;

    @Column(name = "img_id", nullable = false)
    private Integer imgId;

    @Column(name = "analyzed_by", nullable = false)
    private Integer analyzedBy;

    @Column(name = "diagnosis", columnDefinition = "TEXT")
    private String diagnosis;

    @Column(name = "confidence_score", precision = 5, scale = 2)
    private BigDecimal confidenceScore;

    @Column(name = "analysis_date")
    private LocalDateTime analysisDate;

    // Constructors
    public DiagnosisResult() {}

    public DiagnosisResult(Integer resultId, Integer imgId, Integer analyzedBy, String diagnosis,
                           BigDecimal confidenceScore, LocalDateTime analysisDate) {
        this.resultId = resultId;
        this.imgId = imgId;
        this.analyzedBy = analyzedBy;
        this.diagnosis = diagnosis;
        this.confidenceScore = confidenceScore;
        this.analysisDate = analysisDate;
    }

    // Getters and Setters
    public Integer getResultId() {
        return resultId;
    }

    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public Integer getAnalyzedBy() {
        return analyzedBy;
    }

    public void setAnalyzedBy(Integer analyzedBy) {
        this.analyzedBy = analyzedBy;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public BigDecimal getConfidenceScore() {
        return confidenceScore;
    }

    public void setConfidenceScore(BigDecimal confidenceScore) {
        this.confidenceScore = confidenceScore;
    }

    public LocalDateTime getAnalysisDate() {
        return analysisDate;
    }

    public void setAnalysisDate(LocalDateTime analysisDate) {
        this.analysisDate = analysisDate;
    }
}

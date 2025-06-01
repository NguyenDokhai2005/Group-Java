package com.example.DICOM.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data

public class DiagnosisResultsDTO {
    private Integer resultId;
    private Integer imgId;
    private Integer analyzedBy;
    private String diagnosis;
    private BigDecimal confidenceScore;
    private LocalDateTime analysisDate;

    public Integer getResultId() {
        return this.resultId;
    }

    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }

    public Integer getImgId() {
        return this.imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public Integer getAnalyzedBy() {
        return this.analyzedBy;
    }

    public void setAnalyzedBy(Integer analyzedBy) {
        this.analyzedBy = analyzedBy;
    }

    public String getDiagnosis() {
        return this.diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public BigDecimal getConfidenceScore() {
        return this.confidenceScore;
    }

    public void setConfidenceScore(BigDecimal confidenceScore) {
        this.confidenceScore = confidenceScore;
    }

    public LocalDateTime getAnalysisDate() {
        return this.analysisDate;
    }

    public void setAnalysisDate(LocalDateTime analysisDate) {
        this.analysisDate = analysisDate;
    }
}

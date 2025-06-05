package com.example.DICOM.DTO;

import java.time.LocalDateTime;

public class DiagnosisResultDTO {

    private Long image_id;
    private Long analyzed_by_user_id;
    private String diagnosis;
    private Double confidence_score;
    private LocalDateTime analysis_date;

    public DiagnosisResultDTO() {}

    public DiagnosisResultDTO(Long image_id, Long analyzed_by_user_id, String diagnosis, Double confidence_score, LocalDateTime analysis_date) {
        this.image_id = image_id;
        this.analyzed_by_user_id = analyzed_by_user_id;
        this.diagnosis = diagnosis;
        this.confidence_score = confidence_score;
        this.analysis_date = analysis_date;
    }

    public Long getImage_id() {
        return image_id;
    }

    public void setImage_id(Long image_id) {
        this.image_id = image_id;
    }

    public Long getAnalyzed_by_user_id() {
        return analyzed_by_user_id;
    }

    public void setAnalyzed_by_user_id(Long analyzed_by_user_id) {
        this.analyzed_by_user_id = analyzed_by_user_id;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Double getConfidence_score() {
        return confidence_score;
    }

    public void setConfidence_score(Double confidence_score) {
        this.confidence_score = confidence_score;
    }

    public LocalDateTime getAnalysis_date() {
        return analysis_date;
    }

    public void setAnalysis_date(LocalDateTime analysis_date) {
        this.analysis_date = analysis_date;
    }
}

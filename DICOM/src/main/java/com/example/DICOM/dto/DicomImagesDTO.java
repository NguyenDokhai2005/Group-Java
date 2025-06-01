package com.example.DICOM.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DicomImagesDTO {
    private Integer imgId;
    private Integer patientId;
    private Integer uploadedBy;
    private String modality;
    private LocalDate imageDate;
    private String filePath;
    private LocalDateTime createdAt;

    public Integer getImgId() {
        return this.imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public Integer getPatientId() {
        return this.patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getUploadedBy() {
        return this.uploadedBy;
    }

    public void setUploadedBy(Integer uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public String getModality() {
        return this.modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    public LocalDate getImageDate() {
        return this.imageDate;
    }

    public void setImageDate(LocalDate imageDate) {
        this.imageDate = imageDate;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

package com.example.DICOM.dto;

import jakarta.persistence.Entity;
import java.time.LocalDateTime;
@Entity
public class AnnotationsDTO {
    private Integer annotationId;
    private Integer imgId;
    private Integer userId;
    private String annotationText;
    private LocalDateTime createdAt;

    public Integer getAnnotationId() {
        return this.annotationId;
    }

    public void setAnnotationId(Integer annotationId) {
        this.annotationId = annotationId;
    }

    public Integer getImgId() {
        return this.imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAnnotationText() {
        return this.annotationText;
    }

    public void setAnnotationText(String annotationText) {
        this.annotationText = annotationText;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

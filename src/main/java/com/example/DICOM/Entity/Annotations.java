package com.example.DICOM.Entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "annotations")
public class Annotations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long annotationId;

    @ManyToOne
    @JoinColumn(name = "img_id")
    private DicomImage dicomImage;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String note;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt = new Date();

    public Annotations() {}

    public Long getAnnotationId() {
        return annotationId;
    }

    public void setAnnotationId(Long annotationId) {
        this.annotationId = annotationId;
    }

    public DicomImage getDicomImage() {
        return dicomImage;
    }

    public void setDicomImage(DicomImage dicomImage) {
        this.dicomImage = dicomImage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

package com.example.DICOM.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "dicom_images")
public class DicomImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    private Long id;

    @Column(name = "modality")
    private String modality;

    @Column(name = "image_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date imageDate;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    // Quan hệ với bệnh nhân
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    // Người dùng upload
    @ManyToOne
    @JoinColumn(name = "uploaded_by")
    private User uploadedBy;

    // Người dùng liên quan khác (nếu cần)
    @Column(name = "user_id")
    private Long userId;


}

package com.example.DICOM.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "annotations")
public class Annotations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "annotation_id")
    private Long annotationId;

    @ManyToOne
    @JoinColumn(name = "img_id")
    private DicomImage dicomImage;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "annotation_text", columnDefinition = "TEXT")
    private String annotationText;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;


}

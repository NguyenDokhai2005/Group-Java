package com.example.DICOM.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AnnotationsDTO {
    private Long id;
    private Long imageId;
    private Long userId;
    private String username;
    private String annotationText;
    private String note;
    private Date createdAt;
}
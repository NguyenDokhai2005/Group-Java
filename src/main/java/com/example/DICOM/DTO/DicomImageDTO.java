package com.example.DICOM.DTO;

import java.util.Date;

public class DicomImageDTO {

    private Long patient_id;
    private Long uploaded_by;
    private String modality;
    private Date image_date;
    private String file_path;
    private Date created_at;

    public DicomImageDTO() {}

    public Long getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Long patient_id) {
        this.patient_id = patient_id;
    }

    public Long getUploaded_by() {
        return uploaded_by;
    }

    public void setUploaded_by(Long uploaded_by) {
        this.uploaded_by = uploaded_by;
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    public Date getImage_date() {
        return image_date;
    }

    public void setImage_date(Date image_date) {
        this.image_date = image_date;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}

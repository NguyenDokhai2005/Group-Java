package com.example.DICOM.Repository;

import com.example.DICOM.Entity.DicomImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DicomImageRepository extends JpaRepository<DicomImage, Long> {
}

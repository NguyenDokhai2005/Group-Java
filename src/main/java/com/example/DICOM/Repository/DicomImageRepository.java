package com.example.DICOM.Repository;

import com.example.DICOM.Entity.DicomImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DicomImageRepository extends JpaRepository<DicomImage, Long> {}

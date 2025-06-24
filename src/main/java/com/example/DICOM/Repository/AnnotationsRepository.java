package com.example.DICOM.Repository;

import com.example.DICOM.Entity.Annotations;
import com.example.DICOM.Entity.DicomImage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AnnotationsRepository extends JpaRepository<Annotations, Long> {
    List<Annotations> findByDicomImage(DicomImage dicomImage);
}
package Repository;

import Entity.DicomImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DicomImageRepository extends JpaRepository<DicomImage, Long> {
    List<DicomImage> findByPatient_PatientId(Long patientId);
}

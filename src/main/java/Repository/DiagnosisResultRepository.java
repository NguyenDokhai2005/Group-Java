package Repository;

import Entity.DiagnosisResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiagnosisResultRepository extends JpaRepository<DiagnosisResult, Long> {
    List<DiagnosisResult> findByPatient_PatientId(Long patientId);
    List<DiagnosisResult> findByDicomImage_ImgId(Long imgId);
}

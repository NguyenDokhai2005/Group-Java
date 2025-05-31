package Service;

import Entity.DiagnosisResult;
import Repository.DiagnosisResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiagnosisResultService {

    private final DiagnosisResultRepository diagnosisResultRepository;

    public DiagnosisResultService(DiagnosisResultRepository diagnosisResultRepository) {
        this.diagnosisResultRepository = diagnosisResultRepository;
    }

    public List<DiagnosisResult> getAllResults() {
        return diagnosisResultRepository.findAll();
    }

    public Optional<DiagnosisResult> getResultById(Long id) {
        return diagnosisResultRepository.findById(id);
    }

    public DiagnosisResult saveResult(DiagnosisResult result) {
        return diagnosisResultRepository.save(result);
    }

    public void deleteResult(Long id) {
        diagnosisResultRepository.deleteById(id);
    }

    public List<DiagnosisResult> getResultsByPatientId(Long patientId) {
        return diagnosisResultRepository.findByPatient_PatientId(patientId);
    }

    public List<DiagnosisResult> getResultsByImageId(Long imgId) {
        return diagnosisResultRepository.findByDicomImage_ImgId(imgId);
    }
}

package Service;

import Entity.DicomImage;
import Repository.DicomImageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DicomImageService {

    private final DicomImageRepository dicomImageRepository;

    public DicomImageService(DicomImageRepository dicomImageRepository) {
        this.dicomImageRepository = dicomImageRepository;
    }

    public List<DicomImage> getAllImages() {
        return dicomImageRepository.findAll();
    }

    public Optional<DicomImage> getImageById(Long id) {
        return dicomImageRepository.findById(id);
    }

    public DicomImage saveImage(DicomImage dicomImage) {
        return dicomImageRepository.save(dicomImage);
    }

    public void deleteImage(Long id) {
        dicomImageRepository.deleteById(id);
    }

    public List<DicomImage> getImagesByPatientId(Long patientId) {
        return dicomImageRepository.findByPatient_PatientId(patientId);
    }
}

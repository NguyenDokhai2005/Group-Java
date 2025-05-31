package Service;

import Entity.Annotation;
import Repository.AnnotationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnnotationService {

    private final AnnotationRepository annotationRepository;

    public AnnotationService(AnnotationRepository annotationRepository) {
        this.annotationRepository = annotationRepository;
    }

    public List<Annotation> getAll() {
        return annotationRepository.findAll();
    }

    public Optional<Annotation> getById(Long id) {
        return annotationRepository.findById(id);
    }

    public List<Annotation> getByImageId(Long imgId) {
        return annotationRepository.findByDicomImage_ImgId(imgId);
    }

    public Annotation save(Annotation annotation) {
        return annotationRepository.save(annotation);
    }

    public void delete(Long id) {
        annotationRepository.deleteById(id);
    }
}

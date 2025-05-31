package Repository;

import Entity.Annotation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnotationRepository extends JpaRepository<Annotation, Long> {
    List<Annotation> findByDicomImage_ImgId(Long imgId);
}

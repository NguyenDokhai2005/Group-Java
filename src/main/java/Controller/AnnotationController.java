package Controller;

import Entity.Annotation;
import Service.AnnotationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/annotations")
public class AnnotationController {

    private final AnnotationService annotationService;

    public AnnotationController(AnnotationService annotationService) {
        this.annotationService = annotationService;
    }

    @GetMapping
    public List<Annotation> getAll() {
        return annotationService.getAll();
    }

    @GetMapping("/{id}")
    public Annotation getById(@PathVariable Long id) {
        return annotationService.getById(id).orElse(null);
    }

    @GetMapping("/image/{imgId}")
    public List<Annotation> getByImageId(@PathVariable Long imgId) {
        return annotationService.getByImageId(imgId);
    }

    @PostMapping
    public Annotation create(@RequestBody Annotation annotation) {
        return annotationService.save(annotation);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        annotationService.delete(id);
    }
}

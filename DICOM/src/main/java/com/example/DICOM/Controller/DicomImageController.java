package Controller;

import Entity.DicomImage;
import Service.DicomImageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/images")
public class DicomImageController {

    private final DicomImageService dicomImageService;

    public DicomImageController(DicomImageService dicomImageService) {
        this.dicomImageService = dicomImageService;
    }

    @GetMapping
    public List<DicomImage> getAll() {
        return dicomImageService.getAllImages();
    }

    @GetMapping("/{id}")
    public DicomImage getById(@PathVariable Long id) {
        return dicomImageService.getImageById(id).orElse(null);
    }

    @GetMapping("/patient/{patientId}")
    public List<DicomImage> getByPatientId(@PathVariable Long patientId) {
        return dicomImageService.getImagesByPatientId(patientId);
    }

    @PostMapping
    public DicomImage upload(@RequestBody DicomImage image) {
        return dicomImageService.saveImage(image);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        dicomImageService.deleteImage(id);
    }
}
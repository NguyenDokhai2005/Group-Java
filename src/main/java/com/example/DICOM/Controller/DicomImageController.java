package com.example.DICOM.Controller;

import com.example.DICOM.Entity.DicomImage;
import com.example.DICOM.Service.DicomImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/images")
@CrossOrigin("*")
public class DicomImageController {

    @Autowired
    private DicomImageService dicomImageService;

    @PostMapping("/create")
    public DicomImage uploadDicomImage(@RequestBody DicomImage dicomImage) {
        return dicomImageService.createDicomImage(dicomImage);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<DicomImage>> getAllImages() {
        List<DicomImage> images = dicomImageService.getAllImages();
        return ResponseEntity.ok(images);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<DicomImage>> getImagesByPatientId(@PathVariable Long patientId) {
        List<DicomImage> images = dicomImageService.getImagesByPatientId(patientId);
        return ResponseEntity.ok(images);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DicomImage> updateImage(@PathVariable Long id, @RequestBody DicomImage dicomImage) {
        DicomImage updated = dicomImageService.updateImage(id, dicomImage);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable Long id) {
        dicomImageService.deleteImageById(id);
        return ResponseEntity.ok("Đã xóa ảnh có ID: " + id);
    }
}

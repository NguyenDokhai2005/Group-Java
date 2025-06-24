package com.example.DICOM.Controller;

import com.example.DICOM.DTO.AnnotationsDTO;
import com.example.DICOM.Entity.Annotations;
import com.example.DICOM.Service.AnnotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/annotations")
//@CrossOrigin("*")
public class AnnotationsController {

    @Autowired private AnnotationService annotationService;

    @PreAuthorize("hasAuthority('DOCTOR')")
    @PostMapping("/create")
    public ResponseEntity<Annotations> createAnnotation(@RequestBody Annotations annotation) {
        return ResponseEntity.ok(annotationService.createAnnotation(annotation));
    }

    @PreAuthorize("hasAuthority('DOCTOR')")
    @GetMapping("/image/{imageId}")
    public ResponseEntity<List<AnnotationsDTO>> getAnnotationsByImage(@PathVariable Long imageId) {
        return ResponseEntity.ok(annotationService.getAnnotationDTOsByImageId(imageId));
    }
}
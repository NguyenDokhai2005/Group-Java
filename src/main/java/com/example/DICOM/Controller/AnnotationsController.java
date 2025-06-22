//package com.example.DICOM.Controller;
//
//
//import com.example.DICOM.DTO.AnnotationsDTO;
//import com.example.DICOM.Service.AnnotationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/annotations")
//public class AnnotationsController {
//
//    @Autowired
//    private AnnotationService annotationService;
//
//    @PostMapping("/create")
//    public ResponseEntity<?> create(@RequestBody AnnotationsDTO dto) {
//        AnnotationsDTO created = annotationService.createAnnotations(dto);
//        if (created != null) {
//            return ResponseEntity.ok(created);
//        } else {
//            return ResponseEntity.badRequest().build();
//        }
//    }
//}

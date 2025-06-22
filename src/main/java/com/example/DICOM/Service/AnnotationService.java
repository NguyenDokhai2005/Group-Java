//package com.example.DICOM.Service;
//
//
//import com.example.DICOM.DTO.AnnotationsDTO;
//import com.example.DICOM.Entity.Annotations;
//import com.example.DICOM.Entity.DicomImage;
//import com.example.DICOM.Entity.User;
//import com.example.DICOM.Mapper.AnnotationsMapper;
//import com.example.DICOM.Repository.AnnotationsRepository;
//import com.example.DICOM.Repository.DicomImageRepository;
//import com.example.DICOM.Repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.lang.annotation.Annotation;
//import java.util.Optional;
//
//@Service
//public class AnnotationService {
//
//    @Autowired
//    private AnnotationsRepository annotationsRepository;
//
//    @Autowired
//    DicomImageRepository dicomImageRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    public AnnotationsDTO createAnnotations(AnnotationsDTO annotationsDTO) {
//        Annotations annotations = new Annotations();
//
//        Optional<DicomImage> image = dicomImageRepository.findById(annotationsDTO.getImgId());
//        Optional<User> user = userRepository.findById(annotationsDTO.getUserId());
//
//        if (image.isPresent() && user.isPresent()) {
//            annotations.setDicomImage(image.get());
//            annotations.setUser(user.get());
//            annotations.setNote(annotations.getNote());
//            annotations.setCreatedAt(annotations.getCreatedAt());
//
//            Annotations saved = annotationsRepository.save(annotations);
//            return AnnotationsMapper.toDTO(saved);
//        }
//        return null;
//    }
//}

package com.example.DICOM.Service;

import com.example.DICOM.DTO.AnnotationsDTO;
import com.example.DICOM.Entity.Annotations;
import com.example.DICOM.Entity.DicomImage;
import com.example.DICOM.Entity.User;
import com.example.DICOM.Mapper.AnnotationsMapper;
import com.example.DICOM.Repository.AnnotationsRepository;
import com.example.DICOM.Repository.DicomImageRepository;
import com.example.DICOM.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
public class AnnotationService {

    @Autowired private AnnotationsRepository annotationRepository;
    @Autowired private DicomImageRepository dicomImageRepository;
    @Autowired private UserRepository userRepository;

    public Annotations createAnnotation(Annotations annotation) {
        if (annotation.getDicomImage() == null || annotation.getDicomImage().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Thiếu ảnh để chú thích.");
        }
        if (annotation.getUser() == null || annotation.getUser().getUserId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Thiếu người dùng chú thích.");
        }

        DicomImage dicomImage = dicomImageRepository.findById(annotation.getDicomImage().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ảnh không tồn tại."));

        User user = userRepository.findById(annotation.getUser().getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Người dùng không tồn tại."));

        annotation.setDicomImage(dicomImage);
        annotation.setUser(user);
        annotation.setCreatedAt(new Date());

        return annotationRepository.save(annotation);
    }

    public List<AnnotationsDTO> getAnnotationDTOsByImageId(Long imageId) {
        DicomImage image = dicomImageRepository.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy ảnh."));

        return annotationRepository.findByDicomImage(image)
                .stream()
                .map(AnnotationsMapper::toDTO)
                .toList();
    }
}
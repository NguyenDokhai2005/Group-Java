package com.example.DICOM.Service;

import com.example.DICOM.DTO.DicomImageDTO;
import com.example.DICOM.Entity.DicomImage;
import com.example.DICOM.Repository.DicomImageRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DicomImageService {

    private static final Logger logger = LoggerFactory.getLogger(DicomImageService.class);

    private final DicomImageRepository dicomImageRepository;

    public DicomImageService(DicomImageRepository dicomImageRepository) {
        this.dicomImageRepository = dicomImageRepository;
    }

    public boolean saveDicomImage(DicomImageDTO dto) {
        try {
            DicomImage image = new DicomImage();
            image.setPatientId(dto.getPatient_id());
            image.setUploadedBy(dto.getUploaded_by());
            image.setModality(dto.getModality());
            image.setImageDate(dto.getImage_date());
            image.setFilePath(dto.getFile_path());
            image.setCreatedAt(dto.getCreated_at());

            dicomImageRepository.save(image);
            return true;
        } catch (Exception e) {
            logger.error("Failed to save DICOM image", e);
            return false;
        }
    }

    public List<DicomImageDTO> getAllImages() {
        List<DicomImageDTO> dtoList = new ArrayList<>();
        List<DicomImage> imageList = dicomImageRepository.findAll();

        for (DicomImage img : imageList) {
            DicomImageDTO dto = new DicomImageDTO();
            dto.setPatient_id(img.getPatientId());
            dto.setUploaded_by(img.getUploadedBy());
            dto.setModality(img.getModality());
            dto.setImage_date(img.getImageDate());
            dto.setFile_path(img.getFilePath());
            dto.setCreated_at(img.getCreatedAt());
            dtoList.add(dto);
        }

        return dtoList;
    }
}

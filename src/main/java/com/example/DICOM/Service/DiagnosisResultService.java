package com.example.DICOM.Service;

import com.example.DICOM.DTO.DiagnosisResultDTO;
import com.example.DICOM.Entity.DiagnosisResult;
import com.example.DICOM.Entity.DicomImage;
import com.example.DICOM.Entity.User;
import com.example.DICOM.Repository.DiagnosisResultRepository;
import com.example.DICOM.Repository.DicomImageRepository;
import com.example.DICOM.Repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiagnosisResultService {

    private static final Logger logger = LoggerFactory.getLogger(DiagnosisResultService.class);

    private final DiagnosisResultRepository diagnosisResultRepository;
    private final DicomImageRepository dicomImageRepository;
    private final UserRepository userRepository;

    public DiagnosisResultService(DiagnosisResultRepository diagnosisResultRepository,
                                  DicomImageRepository dicomImageRepository,
                                  UserRepository userRepository) {
        this.diagnosisResultRepository = diagnosisResultRepository;
        this.dicomImageRepository = dicomImageRepository;
        this.userRepository = userRepository;
    }

    public boolean createDiagnosisResult(DiagnosisResultDTO dto) {
        try {
            DicomImage image = dicomImageRepository.findById(dto.getImage_id())
                    .orElseThrow(() -> new RuntimeException("Image not found with ID: " + dto.getImage_id()));
            User user = userRepository.findById(dto.getAnalyzed_by_user_id())
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + dto.getAnalyzed_by_user_id()));

            DiagnosisResult result = new DiagnosisResult();
            result.setImage(image);
            result.setAnalyzedBy(user);
            result.setDiagnosis(dto.getDiagnosis());
            result.setConfidenceScore(dto.getConfidence_score());
            result.setAnalysisDate(dto.getAnalysis_date());

            diagnosisResultRepository.save(result);
            return true;
        } catch (Exception e) {
            logger.error("Failed to create diagnosis result", e);
            return false;
        }
    }

    public List<DiagnosisResultDTO> getByUser(Long userId) {
        List<DiagnosisResultDTO> dtoList = new ArrayList<>();
        List<DiagnosisResult> results = diagnosisResultRepository.findByAnalyzedBy_UserId(userId);
        for (DiagnosisResult result : results) {
            dtoList.add(toDTO(result));
        }
        return dtoList;
    }

    public List<DiagnosisResultDTO> getByImage(Long imageId) {
        List<DiagnosisResultDTO> dtoList = new ArrayList<>();
        List<DiagnosisResult> results = diagnosisResultRepository.findByImage_Id(imageId);
        for (DiagnosisResult result : results) {
            dtoList.add(toDTO(result));
        }
        return dtoList;
    }

    private DiagnosisResultDTO toDTO(DiagnosisResult result) {
        DiagnosisResultDTO dto = new DiagnosisResultDTO();
        dto.setImage_id(result.getImage().getId());
        dto.setAnalyzed_by_user_id(result.getAnalyzedBy().getUserId());
        dto.setDiagnosis(result.getDiagnosis());
        dto.setConfidence_score(result.getConfidenceScore());
        dto.setAnalysis_date(result.getAnalysisDate());
        return dto;
    }
}

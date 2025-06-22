package com.example.DICOM.Service;

import com.example.DICOM.Entity.DicomImage;
import com.example.DICOM.Entity.Patient;
import com.example.DICOM.Entity.User;
import com.example.DICOM.Repository.DicomImageRepository;
import com.example.DICOM.Repository.PatientRepository;
import com.example.DICOM.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class DicomImageService {

    @Autowired
    private DicomImageRepository dicomImageRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserRepository userRepository;

    public DicomImage createDicomImage(DicomImage dicomImage) {

        // 1. Kiểm tra Patient có tồn tại
        if (dicomImage.getPatient() != null) {
            Optional<Patient> patientOpt = patientRepository.findById(dicomImage.getPatient().getPatientId());
            if (patientOpt.isPresent()) {
                dicomImage.setPatient(patientOpt.get());
            } else {
                throw new IllegalArgumentException("Patient không tồn tại.");
            }
        } else {
            throw new IllegalArgumentException("Thiếu thông tin patient_id.");
        }

        // 2. Kiểm tra User upload có tồn tại
        if (dicomImage.getUploadedBy() != null && dicomImage.getUploadedBy().getUserId() != null) {
            Optional<User> userOpt = userRepository.findById(dicomImage.getUploadedBy().getUserId());
            if (userOpt.isPresent()) {
                dicomImage.setUploadedBy(userOpt.get());
            } else {
                throw new IllegalArgumentException("User không tồn tại.");
            }
        } else {
            throw new IllegalArgumentException("Thiếu thông tin user_id.");
        }

        // 3. Thiết lập ngày tạo
        dicomImage.setCreatedAt(new Date());

        // 4. Lưu ảnh DICOM
        DicomImage savedImage = dicomImageRepository.save(dicomImage);


        return savedImage;
    }
}

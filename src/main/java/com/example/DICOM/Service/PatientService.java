package com.example.DICOM.Service;

import com.example.DICOM.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.DICOM.DTO.PatientDTO;

import java.util.List;

@Service
public interface PatientService {
    PatientDTO createPatient(PatientDTO patientDTO);
    List<PatientDTO> getAllPatients();
    PatientDTO getPatientById(Long id);
    void deletePatient(Long id);
    PatientDTO updatePatient(Long id, PatientDTO dto);
}

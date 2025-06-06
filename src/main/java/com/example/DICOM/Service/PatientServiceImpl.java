package com.example.DICOM.Service;

import com.example.DICOM.DTO.PatientDTO;
import com.example.DICOM.Entity.Patient;
import com.example.DICOM.Enums.Gender;
import com.example.DICOM.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    // Helper: Convert DTO -> Entity
    private Patient convertToEntity(PatientDTO dto) {
        Patient patient = new Patient();
        patient.setPatientId(dto.getPatientId());
        patient.setName(dto.getName());
        patient.setDateOfBirth(dto.getDateOfBirth());
        patient.setGender(dto.getGender());
        patient.setContactInfo(dto.getContactInfo());
        patient.setCreatedBy(dto.getCreatedBy());
        patient.setCreatedAt(dto.getCreatedAt());
        return patient;
    }

    // Helper: Convert Entity -> DTO
    private PatientDTO convertToDTO(Patient patient) {
        PatientDTO dto = new PatientDTO();
        dto.setPatientId(patient.getPatientId());
        dto.setName(patient.getName());
        dto.setDateOfBirth(patient.getDateOfBirth());
        dto.setGender(patient.getGender());
        dto.setContactInfo(patient.getContactInfo());
        dto.setCreatedBy(patient.getCreatedBy());
        dto.setCreatedAt(patient.getCreatedAt());
        return dto;
    }

    @Override
    public PatientDTO createPatient(PatientDTO dto) {
        Patient patient = convertToEntity(dto);
        Patient saved = patientRepository.save(patient);
        return convertToDTO(saved);
    }

    @Override
    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PatientDTO getPatientById(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        return patient.map(this::convertToDTO).orElse(null);
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
    @Override
    public PatientDTO updatePatient(Long id, PatientDTO dto) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);

        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            patient.setName(dto.getName());
            patient.setDateOfBirth(dto.getDateOfBirth());
            patient.setGender(dto.getGender());
            patient.setContactInfo(dto.getContactInfo());
            patient.setCreatedBy(dto.getCreatedBy());
            patient.setCreatedAt(dto.getCreatedAt());

            Patient updated = patientRepository.save(patient);
            return convertToDTO(updated);
        } else {
            return null; // Không tìm thấy => báo ở Controller
        }
    }

}


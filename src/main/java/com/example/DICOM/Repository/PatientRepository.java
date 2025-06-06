package com.example.DICOM.Repository;

import com.example.DICOM.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findPatientByContactInfo(String contactInfo);
}

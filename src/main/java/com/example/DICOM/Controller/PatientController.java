package com.example.DICOM.Controller;

import com.example.DICOM.DTO.PatientDTO;
import com.example.DICOM.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "*") // Cho phép gọi API từ frontend khác domain
public class PatientController {

    @Autowired
    private PatientService patientService;

    // [POST] Tạo mới bệnh nhân
    @PostMapping
    public PatientDTO createPatient(@RequestBody PatientDTO patientDTO) {
        return patientService.createPatient(patientDTO);
    }

    // [GET] Lấy toàn bộ danh sách bệnh nhân
    @GetMapping
    public List<PatientDTO> getAllPatients() {
        return patientService.getAllPatients();
    }

    // [GET] Lấy bệnh nhân theo ID
    @GetMapping("/{id}")
    public PatientDTO getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    // [DELETE] Xoá bệnh nhân theo ID
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
    }

    // [PUT] Cập nhật bệnh nhân
    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable Long id, @RequestBody PatientDTO dto) {
        PatientDTO updated = patientService.updatePatient(id, dto);

        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(updated);
    }



}

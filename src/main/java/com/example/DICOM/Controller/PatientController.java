package com.example.DICOM.Controller;

import com.example.DICOM.DTO.PatientDTO;
import com.example.DICOM.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
//@CrossOrigin(origins = "*") // Cho phép gọi API từ frontend khác domain
public class PatientController {

    @Autowired
    private PatientService patientService;

    // [POST] Tạo mới bệnh nhân
    @PostMapping("/createPatient")
    public ResponseEntity<?> createPatient(@RequestBody PatientDTO patientDTO) {
        try {
            PatientDTO createdPatient = patientService.createPatient(patientDTO);
            return ResponseEntity.ok(createdPatient);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Tạo bệnh nhân thất bại: " + e.getMessage()); // Trả về 400 Bad Request với thông báo lỗi
        }
    }

    // Lấy danh sách tất cả bệnh nhân
    @GetMapping("/get/getall") // Xử lý yêu cầu GET đến /api/patients
    public ResponseEntity<?> getAllPatients() {
        try {
            List<PatientDTO> patients = patientService.getAllPatients(); // Gọi service để lấy danh sách
            return ResponseEntity.ok(patients); // Trả về 200 OK với danh sách
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lấy danh sách bệnh nhân thất bại: " + e.getMessage()); // Trả về 400 Bad Request với thông báo lỗi
        }
    }

    // Lấy thông tin bệnh nhân theo ID
    @GetMapping("/get/{id}") // Xử lý yêu cầu GET đến /api/patients/{id}
    public ResponseEntity<?> getPatientById(@PathVariable Long id) { // Lấy id từ URL
        try {
            PatientDTO patient = patientService.getPatientById(id); // Gọi service để lấy bệnh nhân
            return ResponseEntity.ok(patient); // Trả về 200 OK với dữ liệu
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lấy thông tin bệnh nhân với ID " + id + " thất bại: " + e.getMessage()); // Trả về 400 Bad Request với thông báo lỗi
        }
    }

    // Cập nhật thông tin bệnh nhân
    @PutMapping("/put/{id}") // Xử lý yêu cầu PUT đến /api/patients/{id}
    public ResponseEntity<?> updatePatient(@PathVariable Long id, @RequestBody PatientDTO patientDTO) { // Lấy id và dữ liệu từ body
        try {
            PatientDTO updatedPatient = patientService.updatePatient(id, patientDTO); // Gọi service để cập nhật
            return ResponseEntity.ok(updatedPatient); // Trả về 200 OK với dữ liệu
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Cập nhật bệnh nhân với ID " + id + " thất bại: " + e.getMessage()); // Trả về 400 Bad Request với thông báo lỗi
        }
    }

    // Xóa bệnh nhân theo ID
    @DeleteMapping("/delete/{id}") // Xử lý yêu cầu DELETE đến /api/patients/{id}
    public ResponseEntity<?> deletePatient(@PathVariable Long id) { // Lấy id từ URL
        try {
            boolean isDeleted = patientService.deletePatient(id); // Gọi service để xóa
            return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.badRequest().body("Xóa bệnh nhân với ID " + id + " thất bại"); // Trả về 200 OK nếu thành công
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Xóa bệnh nhân với ID " + id + " thất bại: " + e.getMessage()); // Trả về 400 Bad Request với thông báo lỗi
        }
    }
}
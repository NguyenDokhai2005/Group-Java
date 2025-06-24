package com.example.DICOM.Service; // Khai báo package chứa lớp Service

import com.example.DICOM.DTO.PatientDTO; // Nhập lớp DTO để ánh xạ dữ liệu
import com.example.DICOM.Entity.Patient; // Nhập thực thể (entity) Patient
import com.example.DICOM.Enums.Gender; // Nhập enum Gender để kiểm tra giới tính
import com.example.DICOM.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; // Nhập annotation để đánh dấu lớp là Service
import jakarta.persistence.EntityManager; // Nhập EntityManager để tương tác với cơ sở dữ liệu
import jakarta.persistence.PersistenceContext; // Nhập annotation để tiêm EntityManager
import jakarta.transaction.Transactional; // Nhập annotation để quản lý giao dịch
import java.util.Date; // Nhập lớp Date để xử lý ngày giờ
import java.util.List; // Nhập List để lưu danh sách
import java.util.stream.Collectors; // Nhập Collectors để gom kết quả từ Stream
import java.util.regex.Pattern; // Nhập Pattern để sử dụng biểu thức chính quy

@Service // Đánh dấu lớp là một Spring Service để được quản lý bởi Spring Container
public class PatientService {
    @Autowired
    private PatientRepository patientRepository; //chạy truy vấn JPQL và lấy danh sách Patient

    //@PersistenceContext đảm bảo rằng entityManager được quản lý bởi container của Spring (context),
    //giúp xử lý các giao dịch và kết nối cơ sở dữ liệu một cách hiệu quả
    @PersistenceContext    // Tiêm một instance của EntityManager do Spring quản lý

    private EntityManager entityManager; // Đối tượng để thực hiện các thao tác CRUD với cơ sở dữ liệu

    // Hàm kiểm tra định dạng số điện thoại
    private boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) return false;
        // Định dạng: +Mã quốc gia (1-3 chữ số) theo sau là 9-11 chữ số
        String phoneRegex = "^\\+?\\d{1,3}?\\d{9,11}$";
        Pattern pattern = Pattern.compile(phoneRegex);
        return pattern.matcher(phoneNumber).matches();
    }

    // Tạo mới một bệnh nhân, sử dụng @Transactional để đảm bảo tính toàn vẹn dữ liệu
    @Transactional
    public PatientDTO createPatient(PatientDTO patientDTO, Long userId) {
        try {
            // Kiểm tra validation
            if (patientDTO.getName() == null || patientDTO.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("Tên bệnh nhân không được để trống hoặc rỗng");
            }
            if (!patientDTO.getName().matches("[a-zA-Z\\sàáảãạăắằẳẵặâầấẩẫậèéẻẽẹêềếểễệìíỉĩịòóỏõọôồốổỗộơờớởỡợùúủũụưừứửữựỳýỷỹỵ]+")) {
                throw new IllegalArgumentException("Tên bệnh nhân chỉ được chứa chữ cái và khoảng trắng");
            }
            if (patientDTO.getDateOfBirth() == null) {
                throw new IllegalArgumentException("Ngày sinh không được để trống");
            }
            if (patientDTO.getDateOfBirth().after(new Date())) {
                throw new IllegalArgumentException("Ngày sinh không được là ngày trong tương lai");
            }
            if (patientDTO.getGender() == null) {
                throw new IllegalArgumentException("Giới tính không được để trống");
            }
            if (!List.of(Gender.MALE, Gender.FEMALE, Gender.OTHER).contains(patientDTO.getGender())) {
                throw new IllegalArgumentException("Giới tính phải là một trong các giá trị: MALE, FEMALE, OTHER");
            }
            if (patientDTO.getContactInfo() == null || patientDTO.getContactInfo().trim().isEmpty()) {
                throw new IllegalArgumentException("Thông tin liên lạc không được để trống hoặc rỗng");
            }
            if (!isValidPhoneNumber(patientDTO.getContactInfo())) {
                throw new IllegalArgumentException("Thông tin liên lạc phải là số điện thoại hợp lệ (có thể bắt đầu bằng mã quốc gia và theo sau là 9-11 chữ số)");
            }
            // Tạo và lưu bệnh nhân
            Patient patient = new Patient();
            patient.setPatientId(patientDTO.getPatientId());
            patient.setName(patientDTO.getName());
            patient.setDateOfBirth(patientDTO.getDateOfBirth());
            patient.setGender(patientDTO.getGender());
            patient.setContactInfo(patientDTO.getContactInfo());
            patient.setCreatedBy(userId);
            patient.setCreatedAt(new Date());

            // Lưu vào cơ sở dữ liệu
            Patient saved = patientRepository.save(patient); // Sử dụng PatientRepository để lưu

            // Tạo DTO trả về
            PatientDTO result = new PatientDTO();
            result.setPatientId(saved.getPatientId());
            result.setName(saved.getName());
            result.setDateOfBirth(saved.getDateOfBirth());
            result.setGender(saved.getGender());
            result.setContactInfo(saved.getContactInfo());
            result.setCreatedAt(saved.getCreatedAt());
            result.setCreatedBy(saved.getCreatedBy());

            return result;
        } catch (Exception e) {
            throw new RuntimeException("Tạo bệnh nhân thất bại: " + e.getMessage(), e);
        }
    }
    // Lấy thông tin bệnh nhân theo ID
    public PatientDTO getPatientById(long patientId) {
        try {
            if (patientId <= 0) { // Kiểm tra ID hợp lệ
                throw new IllegalArgumentException("ID bệnh nhân phải là số dương");
            }
            Patient patient = entityManager.find(Patient.class, patientId);// Tìm Patient theo ID
            if (patient == null) { // Kiểm tra nếu không tìm thấy
                throw new RuntimeException("Không tìm thấy bệnh nhân với ID: " + patientId);
            }
            PatientDTO patientDTO = new PatientDTO();// Tạo mới PatientDTO
            patientDTO.setPatientId(patient.getPatientId());
            patientDTO.setName(patient.getName());
            patientDTO.setDateOfBirth(patient.getDateOfBirth());
            patientDTO.setGender(patient.getGender());
            patientDTO.setContactInfo(patient.getContactInfo());
            patientDTO.setCreatedBy(patient.getCreatedBy());
            patientDTO.setCreatedAt(patient.getCreatedAt());
            return patientDTO;
        } catch (Exception e) {
            throw new RuntimeException("Lấy thông tin bệnh nhân với ID " + patientId + " thất bại: " + e.getMessage(), e);
        }
    }
    // Lấy danh sách tất cả bệnh nhân
    public List<PatientDTO> getAllPatients() {
        try {
            List<Patient> patients = entityManager.createQuery("SELECT p FROM patients p", Patient.class).getResultList(); // Truy vấn tất cả Patient từ cơ sở dữ liệu
            if (patients.isEmpty()) {// Kiểm tra danh sách rỗng hoặc null
                throw new RuntimeException("Không tìm thấy bệnh nhân nào trong cơ sở dữ liệu");
            }
            return patients.stream().map(patient -> {
                PatientDTO patientDTO = new PatientDTO();
                patientDTO.setPatientId(patient.getPatientId());
                patientDTO.setName(patient.getName());
                patientDTO.setDateOfBirth(patient.getDateOfBirth());
                patientDTO.setGender(patient.getGender());
                patientDTO.setContactInfo(patient.getContactInfo());
                patientDTO.setCreatedBy(patient.getCreatedBy());
                patientDTO.setCreatedAt(patient.getCreatedAt());
                return patientDTO;
            }).collect(Collectors.toList());// Gom tất cả PatientDTO thành một List
        } catch (Exception e) {
            throw new RuntimeException("Lấy danh sách tất cả bệnh nhân thất bại: " + e.getMessage(), e);
        }

    }

    // Cập nhật thông tin bệnh nhân
    @Transactional
    public PatientDTO updatePatient(long patientId, PatientDTO patientDTO) {
        try {
            if (patientId <= 0) {
                throw new IllegalArgumentException("ID bệnh nhân phải là số dương");
            }
            if (patientDTO == null) {
                throw new IllegalArgumentException("Dữ liệu bệnh nhân không được để trống");
            }
            if (patientDTO.getName() == null || patientDTO.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("Tên bệnh nhân không được để trống hoặc rỗng");
            }
            if (!patientDTO.getName().matches("[a-zA-Z\\sàáảãạăắằẳẵặâầấẩẫậèéẻẽẹêềếểễệìíỉĩịòóỏõọôồốổỗộơờớởỡợùúủũụưừứửữựỳýỷỹỵ]+")) {
                throw new IllegalArgumentException("Tên bệnh nhân chỉ được chứa chữ cái và khoảng trắng");
            }
            if (patientDTO.getDateOfBirth() == null) {
                throw new IllegalArgumentException("Ngày sinh không được để trống");
            }
            if (patientDTO.getDateOfBirth().after(new Date())) {
                throw new IllegalArgumentException("Ngày sinh không được là ngày trong tương lai");
            }
            if (patientDTO.getGender() == null) {
                throw new IllegalArgumentException("Giới tính không được để trống");
            }
            if (!List.of(Gender.MALE, Gender.FEMALE, Gender.OTHER).contains(patientDTO.getGender())) {
                throw new IllegalArgumentException("Giới tính phải là một trong các giá trị: MALE, FEMALE, OTHER");
            }
            if (patientDTO.getContactInfo() == null || patientDTO.getContactInfo().trim().isEmpty()) {
                throw new IllegalArgumentException("Thông tin liên lạc không được để trống hoặc rỗng");
            }
            if (!isValidPhoneNumber(patientDTO.getContactInfo())) {
                throw new IllegalArgumentException("Thông tin liên lạc phải là số điện thoại hợp lệ (có thể bắt đầu bằng mã quốc gia và theo sau là 9-11 chữ số)");
            }

            Patient patient = entityManager.find(Patient.class, patientId);
            if (patient == null) {
                throw new RuntimeException("Không tìm thấy bệnh nhân với ID: " + patientId);
            }
            patient.setName(patientDTO.getName());
            patient.setDateOfBirth(patientDTO.getDateOfBirth());
            patient.setGender(patientDTO.getGender());
            patient.setContactInfo(patientDTO.getContactInfo());
            patient.setCreatedBy(patientDTO.getCreatedBy());
            patient.setCreatedAt(patient.getCreatedAt());

            entityManager.merge(patient);
            return patientDTO;
        } catch (Exception e) {
            throw new RuntimeException("Cập nhật bệnh nhân với ID " + patientId + " thất bại: " + e.getMessage(), e);
        }
    }
    // Xóa bệnh nhân theo ID
    @Transactional
    public boolean deletePatient(long patientId) {
        try {
            if (patientId <= 0) {
                throw new IllegalArgumentException("ID bệnh nhân phải là số dương");
            }
            Patient patient = entityManager.find(Patient.class, patientId);
            if (patient == null) {
                throw new RuntimeException("Không tìm thấy bệnh nhân với ID: " + patientId);
            }
            entityManager.remove(patient);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Xóa bệnh nhân với ID " + patientId + " thất bại: " + e.getMessage(), e);
        }
    }
}

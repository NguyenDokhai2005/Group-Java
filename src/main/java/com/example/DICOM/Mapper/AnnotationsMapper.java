//package com.example.DICOM.Mapper;
//
//
//import com.example.DICOM.DTO.AnnotationsDTO;
//import com.example.DICOM.Entity.Annotations;
//import com.example.DICOM.Entity.DicomImage;
//import com.example.DICOM.Entity.User;
//
//public class AnnotationsMapper {
//    public static AnnotationsDTO toDTO(Annotations annotation) {
//        if (annotation == null) return null;
//
//        AnnotationsDTO dto = new AnnotationsDTO();
//        dto.setAnnotationId(annotation.getAnnotationId());
//        dto.setNote(annotation.getNote());
//        dto.setCreatedAt(annotation.getCreatedAt());
//
//        if (annotation.getDicomImage() != null)
//            dto.setImgId(annotation.getDicomImage().getImgId());
//
//        if (annotation.getUser() != null)
//            dto.setUserId(annotation.getUser().getUserId());
//
//        return dto;
//    }
//
//    public static Annotations toEntity(AnnotationsDTO dto) {
//        if (dto == null) return null;
//
//        Annotations annotation = new Annotations();
//        annotation.setAnnotationId(dto.getAnnotationId());
//        annotation.setNote(dto.getNote());
//        annotation.setCreatedAt(dto.getCreatedAt());
//
//        // Tạo object DicomImage để set ID (chỉ cần set ID nếu không cần truy vấn DB)
//        DicomImage image = new DicomImage();
//        image.setImgId(dto.getImgId());
//        annotation.setDicomImage(image);
//
//        // Tạo object User để set ID
//        User user = new User();
//        user.setUserId(dto.getUserId());
//        annotation.setUser(user);
//
//        return annotation;
//    }
//}

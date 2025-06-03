package com.example.DICOM.Mapper;

import com.example.DICOM.DTO.UserDTO;
import com.example.DICOM.Entity.User;

public class UserMapper {

    public static UserDTO toDTO(User entity) {
        UserDTO dto = new UserDTO();
        dto.setUserId(entity.getUserId());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setRole(entity.getRole());
        return dto;
    }

    public static User toEntity(UserDTO dto) {
        User entity = new User();
        entity.setUserId(dto.getUserId());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setRole(dto.getRole());
        return entity;
    }
}

package com.example.DICOM.DTO;

import jakarta.validation.constraints.Size;

import java.util.Date;

public class UserDTO {

    private Long userId;

    @Size(min=8, max=20, message = "tạo mật khẩu tối thiểu 8 ký tự tối đa 20 ký tự")
    private String username;
    private String password;
    private String role;
    private Date created_date;

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public UserDTO() {}

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
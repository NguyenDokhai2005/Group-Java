package com.example.DICOM.dto;

import java.time.LocalDateTime;

public class NotificationsDTO {
    private Integer notificationId;
    private Integer userId;
    private String message;
    private Byte isRead;
    private LocalDateTime createdAt;

    public Integer getNotificationId() {
        return this.notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Byte getIsRead() {
        return this.isRead;
    }

    public void setIsRead(Byte isRead) {
        this.isRead = isRead;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

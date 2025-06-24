package com.example.DICOM.Model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class IntrospectResponce {
    private boolean valid;
    private String username;
    private String role;
    private Date expiration;
}

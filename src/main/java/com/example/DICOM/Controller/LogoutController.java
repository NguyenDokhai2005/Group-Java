package com.example.DICOM.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/logout")
public class LogoutController {

    // ⚠️ Danh sách token đã đăng xuất — nên chuyển sang Redis trong production
    private static final Set<String> tokenBlacklist = new HashSet<>();

    @PostMapping
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            tokenBlacklist.add(token);  // ✅ Lưu token đã bị thu hồi
            return ResponseEntity.ok("Đăng xuất thành công.");
        }

        return ResponseEntity.badRequest().body("Không tìm thấy token.");
    }

    // ✅ Hàm hỗ trợ kiểm tra token đã bị thu hồi chưa
    public static boolean isBlacklisted(String token) {
        return tokenBlacklist.contains(token);
    }
}

package com.example.DICOM.Controller;

import com.example.DICOM.DTO.UserDTO;
import com.example.DICOM.Payloads.ResponeData;
import com.example.DICOM.Service.LoginService;
import com.example.DICOM.Service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.DICOM.Entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final UserService userService;
    private LoginService loginService;

    public LoginController(LoginService loginService, UserService userService) {
        this.loginService = loginService;
        this.userService = userService;
    }

    @PostMapping("/singup")
    public ResponseEntity<String> loigin(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        User user = loginService.loginUser(username, password);
        if (user != null) {
            return ResponseEntity.ok("Đăng nhập thành công! Chào " + user.getUsername());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sai tài khoản hoặc mật khẩu");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {


        ResponeData responeData = new ResponeData();
        boolean success = userService.createUser(userDTO);
        if (success) {
            responeData.setSuccess(Boolean.TRUE);
            responeData.setData("User created successfully");
        }else {
            responeData.setSuccess(Boolean.FALSE);
            responeData.setData("User đã tồn tại");
        }
        return new ResponseEntity<>(responeData, HttpStatus.OK);
    }
}

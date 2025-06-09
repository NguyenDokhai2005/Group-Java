package com.example.DICOM.Controller;

import com.example.DICOM.DTO.UserDTO;
import com.example.DICOM.DTO.request.IntrospectRequest;
import com.example.DICOM.DTO.request.LoginRequest;
import com.example.DICOM.Payloads.ResponeData;
import com.example.DICOM.Repository.AuthenticationResponse;
import com.example.DICOM.Repository.IntrospectResponce;
import com.example.DICOM.Service.LoginService;
import com.example.DICOM.Service.UserService;
import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.DICOM.Entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final UserService userService;
    @Autowired
    private LoginService loginService;

    public LoginController(LoginService loginService, UserService userService) {
        this.loginService = loginService;
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        AuthenticationResponse response = loginService.loginUser(request.getUsername(), request.getPassword());

        if (response != null) {
//            System.out.println(response);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Tài khoản hoặc mật khẩu không đúng");
        }
    }

    @PostMapping("/introspect")
    public ResponseEntity<?> login(@RequestBody IntrospectRequest request) throws JOSEException, ParseException {
        try {
            IntrospectResponce responce = LoginService.introspectResponce(request);
            return ResponseEntity.ok(responce);
        } catch (Exception e) {
            return ResponseEntity.ok(IntrospectResponce.builder().valid(false).build());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        ResponeData responeData = new ResponeData();
        boolean success = userService.createUser(userDTO);
        if (success) {
            responeData.setSuccess(Boolean.TRUE);
            responeData.setData("User created successfully");
            return ResponseEntity.ok(responeData);

        }else {
            responeData.setSuccess(Boolean.FALSE);
            responeData.setData("User đã tồn tại");
        }
        return new ResponseEntity<>(responeData, HttpStatus.OK);
    }
}

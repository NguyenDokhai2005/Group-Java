package com.example.DICOM.Controller;

import com.example.DICOM.DTO.UserDTO;
import com.example.DICOM.Entity.User;
import com.example.DICOM.Mapper.UserMapper;
import com.example.DICOM.Payloads.ResponeData;
import com.example.DICOM.Repository.UserRepository;
import com.example.DICOM.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/re")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        ResponeData responeData = new ResponeData();
        boolean success = userService.createUser(userDTO);
        if (success) {
            responeData.setSuccess(Boolean.TRUE);
            responeData.setData("User created successfully");
        }else {
            responeData.setSuccess(Boolean.FALSE);
            responeData.setData("User creation failed");
        }
        return new ResponseEntity<>(responeData, HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<?> getAllUsers() {

        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }


}

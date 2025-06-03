package com.example.DICOM.Controller;

import com.example.DICOM.DTO.UserDTO;
import com.example.DICOM.Entity.User;
import com.example.DICOM.Mapper.UserMapper;
import com.example.DICOM.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAll() {
        return userService.getAllUsers()
                .stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return user != null ? ResponseEntity.ok(UserMapper.toDTO(user)) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO dto) {
        User saved = userService.saveUser(UserMapper.toEntity(dto));
        return ResponseEntity.ok(UserMapper.toDTO(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

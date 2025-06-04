package com.example.DICOM.Service;

import com.example.DICOM.DTO.UserDTO;
import com.example.DICOM.Entity.User;
import com.example.DICOM.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;

    public boolean createUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        user.setCreatedAt(userDTO.getCreated_date());

        try {
            System.out.println("Inserting User");
            userRepository.save(user);
            System.out.println("Inserted User");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else
            return null;
    }


}

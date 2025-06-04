package com.example.DICOM.Service;

import com.example.DICOM.DTO.UserDTO;
import com.example.DICOM.Entity.User;
import com.example.DICOM.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private  UserRepository userRepository;

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

    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOS = new ArrayList<>();
        List<User> userList =  userRepository.findAll();
        for (User user : userList) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(user.getUserId());
            userDTO.setUsername(user.getUsername());
            userDTO.setPassword(user.getPassword());
            userDTO.setRole(user.getRole());
            userDTO.setCreated_date(user.getCreatedAt());
            userDTOS.add(userDTO);
        }

        return userDTOS;
    }
    

}

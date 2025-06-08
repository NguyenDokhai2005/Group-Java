package com.example.DICOM.Service;

import com.example.DICOM.DTO.UserDTO;
import com.example.DICOM.Mapper.UserMapper;
import com.example.DICOM.Entity.User;
import com.example.DICOM.Payloads.ResponeData;
import com.example.DICOM.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean createUser(UserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            System.out.println("Username đã tồn tại" + userDTO.getUsername());
            return false;
        } else {
//            User user = new User();
//            user.setUsername(userDTO.getUsername());
//            user.setPassword(userDTO.getPassword());
//            user.setRole(userDTO.getRole());
//            user.setCreatedAt(userDTO.getCreated_date());
            User user = UserMapper.toEntity(userDTO);

            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(5);
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

            try {
                System.out.println("Inserting User");
                userRepository.save(user);
                System.out.println("Inserted User");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

//        User user = new User();
//        user.setUsername(userDTO.getUsername());
//        user.setPassword(userDTO.getPassword());
//        user.setRole(userDTO.getRole());
//        user.setCreatedAt(userDTO.getCreated_date());
//
//        try {
//            System.out.println("Inserting User");
//            userRepository.save(user);
//            System.out.println("Inserted User");
//            return true;
//        }catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }
    }

    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOS = new ArrayList<>();
        List<User> userList = userRepository.findAll();
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



    public boolean updateUser(Long id, UserDTO userDTO) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            System.out.println("Không tìm thấy User với ID: " + id);
            return false;
        }

        User newUser = new User();

        //update
        if (userDTO.getUsername() != null) {
            newUser.setUsername(userDTO.getUsername());
        }
        if (userDTO.getPassword() != null) {
            newUser.setPassword(userDTO.getPassword());
        }
        if (userDTO.getRole() != null) {
            newUser.setRole(userDTO.getRole());
        }
        if (userDTO.getCreated_date() != null) {
            userDTO.setCreated_date(userDTO.getCreated_date());
        }

        try {
            System.out.println("Updating User");
            userRepository.save(newUser);
            System.out.println("Updated User");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(Long id) {
        if(!userRepository.existsById(id)) {
            return false;
        } else {
            userRepository.deleteById(id);
            System.out.println("Deleted User");
            return true;
        }
    }

}

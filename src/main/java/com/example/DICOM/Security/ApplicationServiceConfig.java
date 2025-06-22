//package com.example.DICOM.Security;
//
//import com.example.DICOM.Enums.Role;
//import com.example.DICOM.Repository.UserRepository;
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.HashSet;
//
//@Configurable
//public class ApplicationServiceConfig {
//
//    @Bean
//    ApplicationRunner applicationRunner(UserRepository userRepository) {
//        return args -> {
//            if (userRepository.findByUsername("admin") == null){
//                var roles = new HashSet<String>();
//                roles.add(Role.ADMIN.name());
//
//                UserDetails user = User.builder()
//                        .username("admin")
//                        .password("admin")
//                        .roles(String.valueOf(roles))
//                        .build();
//            }
//        }
//    }
//}

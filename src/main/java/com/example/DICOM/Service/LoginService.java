package com.example.DICOM.Service;

import com.example.DICOM.DTO.UserDTO;
import com.example.DICOM.Entity.User;
import com.example.DICOM.Repository.AuthenticationResponse;
import com.example.DICOM.Repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;


    private static final Logger log = LoggerFactory.getLogger(LoginService.class);

    protected static final String SINGER_KEY =
            "cc3dbZhXgrUYMKqBhvEw13N8FUtQQEI7Rd9dw1dw2MghUzacDbCmh1oyVfKILKIJ";

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

    private String generateToken(String username) {

        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwsClaimSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer(username)
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.DAYS).toEpochMilli()
                ))

                .build();

        Payload payload = new Payload(jwsClaimSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);

        try{
            jwsObject.sign(new MACSigner(SINGER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e){
            log.error("Cannot create token", e);
            throw new RuntimeException(e);
        }

    }

    public AuthenticationResponse loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean passwordcheck = passwordEncoder.matches(password, user.getPassword());
        if (user != null && passwordcheck ) {
            var token = generateToken(username);
            return AuthenticationResponse.builder()
                    .token(token)
                    .build();
        } else
            return null;
        }


}

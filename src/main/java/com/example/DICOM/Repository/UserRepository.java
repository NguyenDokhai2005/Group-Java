package com.example.DICOM.Repository;

import com.example.DICOM.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User deleteById(long id);
    User findById(long id);
    User findByUsername(String username);
    boolean existsByUsername(String username);
}

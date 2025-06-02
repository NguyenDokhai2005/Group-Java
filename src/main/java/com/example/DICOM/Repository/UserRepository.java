package com.example.DICOM.Repository;

import com.example.DICOM.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Optional: findByUsername, etc.
}

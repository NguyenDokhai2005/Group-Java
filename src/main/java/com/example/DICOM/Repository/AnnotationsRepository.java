package com.example.DICOM.Repository;

import com.example.DICOM.Entity.Annotations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AnnotationsRepository extends JpaRepository<Annotations, Long> {
}

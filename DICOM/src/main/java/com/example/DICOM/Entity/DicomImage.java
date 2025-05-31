package Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "dicom_images")
public class DicomImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imgId;

    private String filePath;

    private String modality; // CT, MRI, etc.

    private LocalDateTime capturedAt;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "uploaded_by")
    private Users auploadedBy;

    private LocalDateTime uploadedAt;

    // Getters and Setters
}

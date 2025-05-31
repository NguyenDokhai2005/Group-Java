package Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "diagnosis_results")
public class DiagnosisResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultId;

    @ManyToOne
    @JoinColumn(name = "img_id")
    private DicomImage dicomImage;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private String diagnosis;

    private String aiConfidence;

    private LocalDateTime diagnosedAt;

    @ManyToOne
    @JoinColumn(name = "diagnosed_by")
    private Users diagnosedBy;

    // Getters and Setters
}

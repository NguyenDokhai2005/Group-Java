package Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;

    private String name;

    private String contactInfo;

    private String gender;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    // Getters and Setters
}

package Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private boolean read;

    private LocalDateTime sentAt;

    public void setRead(boolean b) {
    }

    // Getters and Setters
}


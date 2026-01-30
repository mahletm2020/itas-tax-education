package main.java.com.itas.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "webinars")
@Data
public class Webinar {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "scheduled_time", nullable = false)
    private LocalDateTime scheduledTime;
    
    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;
    
    @Column(name = "max_attendees")
    private Integer maxAttendees = 100;
    
    @Enumerated(EnumType.STRING)
    private WebinarStatus status = WebinarStatus.SCHEDULED;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @OneToMany(mappedBy = "webinar", cascade = CascadeType.ALL)
    private List<WebinarRegistration> registrations = new ArrayList<>();
}

enum WebinarStatus {
    SCHEDULED, LIVE, COMPLETED, CANCELLED
}
package main.java.com.itas.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "resources")
@Data
public class Resource {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String title;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "resource_type", nullable = false)
    private ResourceType type;
    
    @Column(name = "content_url", nullable = false)
    private String contentUrl;
    
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;  // Nullable for free resources
    
    @ManyToOne
    @JoinColumn(name = "uploaded_by", nullable = false)
    private User uploadedBy;
    
    @Column(name = "upload_date")
    private LocalDateTime uploadDate = LocalDateTime.now();
    
    @OneToMany(mappedBy = "resource", cascade = CascadeType.ALL)
    private List<Progress> progressRecords = new ArrayList<>();
}

enum ResourceType {
    VIDEO, PDF, ARTICLE
}
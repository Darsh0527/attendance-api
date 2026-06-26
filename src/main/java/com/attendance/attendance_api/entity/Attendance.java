package com.attendance.attendance_api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "attendance")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String status;

    private String remarks;

    @Column(name = "check_in_time")
    private LocalDateTime checkInTime;

    @PrePersist
    public void prePersist() {
        this.checkInTime = LocalDateTime.now();
        if (this.status == null) this.status = "PRESENT";
    }
}
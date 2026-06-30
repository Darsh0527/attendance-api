package com.attendance.attendance_api.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "attendance")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attendance {

    @Id
    private String id;

    private String userId;

    private String userName;

    private String status;

    private String remarks;

    private LocalDateTime checkInTime;

    public void prePersist() {
        this.checkInTime = LocalDateTime.now();
        if (this.status == null) this.status = "PRESENT";
    }
}
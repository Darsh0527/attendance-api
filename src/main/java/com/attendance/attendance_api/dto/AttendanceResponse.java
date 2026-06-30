package com.attendance.attendance_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceResponse {

    private String id;
    private String userId;
    private String userName;
    private String status;
    private String remarks;
    private LocalDateTime checkInTime;
}
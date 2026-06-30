package com.attendance.attendance_api.service;

import com.attendance.attendance_api.dto.AttendanceRequest;
import com.attendance.attendance_api.dto.AttendanceResponse;
import com.attendance.attendance_api.entity.Attendance;
import com.attendance.attendance_api.entity.User;
import com.attendance.attendance_api.exception.ResourceNotFoundException;
import com.attendance.attendance_api.repository.AttendanceRepository;
import com.attendance.attendance_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;

    public AttendanceResponse markAttendance(AttendanceRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + request.getUserId()));

        Attendance attendance = Attendance.builder()
                .userId(user.getId())
                .userName(user.getName())
                .status(request.getStatus() != null ? request.getStatus() : "PRESENT")
                .remarks(request.getRemarks())
                .build();
        attendance.prePersist();
        Attendance saved = attendanceRepository.save(attendance);
        return mapToResponse(saved);
    }

    public List<AttendanceResponse> getAllAttendance() {
        return attendanceRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public AttendanceResponse getAttendanceById(String id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not found with id: " + id));
        return mapToResponse(attendance);
    }

    public List<AttendanceResponse> getAttendanceByUser(String userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return attendanceRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<AttendanceResponse> getAttendanceByUserAndStatus(String userId, String status) {
        return attendanceRepository.findByUserIdAndStatus(userId, status)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<AttendanceResponse> getAttendanceByStatus(String status) {
        return attendanceRepository.findByStatus(status)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public AttendanceResponse updateAttendance(String id, AttendanceRequest request) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not found with id: " + id));
        attendance.setStatus(request.getStatus());
        attendance.setRemarks(request.getRemarks());
        Attendance updated = attendanceRepository.save(attendance);
        return mapToResponse(updated);
    }

    public void deleteAttendance(String id) {
        attendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not found with id: " + id));
        attendanceRepository.deleteById(id);
    }

    private AttendanceResponse mapToResponse(Attendance attendance) {
        return AttendanceResponse.builder()
                .id(attendance.getId())
                .userId(attendance.getUserId())
                .userName(attendance.getUserName())
                .status(attendance.getStatus())
                .remarks(attendance.getRemarks())
                .checkInTime(attendance.getCheckInTime())
                .build();
    }
}
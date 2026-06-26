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
                .user(user)
                .status(request.getStatus() != null ? request.getStatus() : "PRESENT")
                .remarks(request.getRemarks())
                .build();
        Attendance saved = attendanceRepository.save(attendance);
        return mapToResponse(saved);
    }

    public List<AttendanceResponse> getAllAttendance() {
        return attendanceRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public AttendanceResponse getAttendanceById(Long id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not found with id: " + id));
        return mapToResponse(attendance);
    }

    public List<AttendanceResponse> getAttendanceByUser(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return attendanceRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<AttendanceResponse> getAttendanceByUserAndStatus(Long userId, String status) {
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

    public AttendanceResponse updateAttendance(Long id, AttendanceRequest request) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not found with id: " + id));
        attendance.setStatus(request.getStatus());
        attendance.setRemarks(request.getRemarks());
        Attendance updated = attendanceRepository.save(attendance);
        return mapToResponse(updated);
    }

    public void deleteAttendance(Long id) {
        attendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not found with id: " + id));
        attendanceRepository.deleteById(id);
    }

    private AttendanceResponse mapToResponse(Attendance attendance) {
        return AttendanceResponse.builder()
                .id(attendance.getId())
                .userId(attendance.getUser().getId())
                .userName(attendance.getUser().getName())
                .status(attendance.getStatus())
                .remarks(attendance.getRemarks())
                .checkInTime(attendance.getCheckInTime())
                .build();
    }
}
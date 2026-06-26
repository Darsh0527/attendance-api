package com.attendance.attendance_api.controller;

import com.attendance.attendance_api.dto.AttendanceRequest;
import com.attendance.attendance_api.dto.AttendanceResponse;
import com.attendance.attendance_api.service.AttendanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    // MARK ATTENDANCE — CREATE
    @PostMapping
    public ResponseEntity<AttendanceResponse> markAttendance(@Valid @RequestBody AttendanceRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(attendanceService.markAttendance(request));
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<AttendanceResponse>> getAllAttendance() {
        return ResponseEntity.ok(attendanceService.getAllAttendance());
    }

    // GET BY ID — index based
    @GetMapping("/{id}")
    public ResponseEntity<AttendanceResponse> getAttendanceById(@PathVariable Long id) {
        return ResponseEntity.ok(attendanceService.getAttendanceById(id));
    }

    // GET ALL ATTENDANCE FOR A USER — index based
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AttendanceResponse>> getAttendanceByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(attendanceService.getAttendanceByUser(userId));
    }

    // GET BY USER + STATUS — index based
    @GetMapping("/user/{userId}/status/{status}")
    public ResponseEntity<List<AttendanceResponse>> getByUserAndStatus(@PathVariable Long userId,
                                                                       @PathVariable String status) {
        return ResponseEntity.ok(attendanceService.getAttendanceByUserAndStatus(userId, status));
    }

    // GET BY STATUS — index based
    @GetMapping("/status/{status}")
    public ResponseEntity<List<AttendanceResponse>> getByStatus(@PathVariable String status) {
        return ResponseEntity.ok(attendanceService.getAttendanceByStatus(status));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<AttendanceResponse> updateAttendance(@PathVariable Long id,
                                                               @Valid @RequestBody AttendanceRequest request) {
        return ResponseEntity.ok(attendanceService.updateAttendance(id, request));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
        return ResponseEntity.noContent().build();
    }
}

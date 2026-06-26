package com.attendance.attendance_api.repository;

import com.attendance.attendance_api.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByUserId(Long userId);

    List<Attendance> findByUserIdAndStatus(Long userId, String status);

    List<Attendance> findByStatus(String status);
}
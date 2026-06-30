package com.attendance.attendance_api.repository;

import com.attendance.attendance_api.entity.Attendance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AttendanceRepository extends MongoRepository<Attendance, String> {

    List<Attendance> findByUserId(String userId);

    List<Attendance> findByUserIdAndStatus(String userId, String status);

    List<Attendance> findByStatus(String status);
}
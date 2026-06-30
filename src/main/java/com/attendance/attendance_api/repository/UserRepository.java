package com.attendance.attendance_api.repository;

import com.attendance.attendance_api.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);

    List<User> findByDepartment(String department);

    boolean existsByEmail(String email);
}
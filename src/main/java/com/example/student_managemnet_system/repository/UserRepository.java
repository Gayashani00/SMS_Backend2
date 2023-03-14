package com.example.student_managemnet_system.repository;

import com.example.student_managemnet_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}

package com.example.SchoolManager.repository;

import com.example.SchoolManager.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}

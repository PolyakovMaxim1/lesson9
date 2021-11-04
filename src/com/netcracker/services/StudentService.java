package com.netcracker.services;

import com.netcracker.domain.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAll();
    Student addStudent(Student student);
    boolean deleteStudent(int id);
    Student getById(int id);
}

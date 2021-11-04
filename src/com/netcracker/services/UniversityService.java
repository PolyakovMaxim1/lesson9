package com.netcracker.services;

import com.netcracker.annotations.Inject;
import com.netcracker.domain.Student;

import java.util.List;

public class UniversityService {

    @Inject(className = "FirstService")
    private StudentService studentService;

    public UniversityService() { }

    public StudentService getStudentService() {
        return studentService;
    }

    public List<Student> getAllStudents() {
        return studentService.getAll();
    }
}

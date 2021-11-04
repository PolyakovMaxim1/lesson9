package com.netcracker;

import com.netcracker.domain.Student;
import com.netcracker.factory.Factory;
import com.netcracker.services.StudentService;
import com.netcracker.services.UniversityService;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        try {
            Factory factory = Factory.getInstance(Paths.get(".", "src", "com", "netcracker", "services"));
            UniversityService universityService = (UniversityService) Factory.getObjects().get("UniversityService");
            StudentService studentService = universityService.getStudentService();
            List<Student> students = studentService.getAll();
            students.forEach(System.out::println);
        } catch (IOException | InstantiationException | IllegalAccessException exception) {
            exception.printStackTrace();
        }
    }
}

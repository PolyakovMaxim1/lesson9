package com.netcracker.domain;

public class Student {
    private int id;
    private int age;
    private String name;
    private Faculty faculty;

    public Student(int age, String name, Faculty faculty) {
        this.age = age;
        this.name = name;
        this.faculty = faculty;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", faculty=" + faculty +
                '}';
    }
}

package org.example.hexlet.dto.courses;

import org.example.hexlet.model.Course;

import java.util.List;

public class CoursesPage {
    private List<Course> courses;
    private String header;

    // Конструктор
    public CoursesPage(List<Course> courses, String header) {
        this.courses = courses;
        this.header = header;
    }

    // Геттеры
    public List<Course> getCourses() {
        return courses;
    }

    public String getHeader() {
        return header;
    }
}

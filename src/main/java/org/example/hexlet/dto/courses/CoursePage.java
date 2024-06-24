package org.example.hexlet.dto.courses;

import org.example.hexlet.model.Course;

public class CoursePage {
    private Course course;
    private String header;

    // Конструктор
    public CoursePage(Course course, String header) {
        this.course = course;
        this.header = header;
    }

    // Геттер для course
    public Course getCourse() {
        return course;
    }

    // Геттер для header
    public String getHeader() {
        return header;
    }
}

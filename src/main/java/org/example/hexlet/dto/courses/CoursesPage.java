package org.example.hexlet.dto.courses;

import org.example.hexlet.model.Course;

import java.util.List;

public class CoursesPage {
    private List<Course> courses;
    private String header;
    private String term;

    // Конструктор
    public CoursesPage(List<Course> courses, String header) {
        this.courses = courses;
        this.header = header;
        this.term = term;
    }

    // Геттеры
    public List<Course> getCourses() {
        return courses;
    }

    public String getHeader() {
        return header;
    }

    public String getTerm() {
        return term;
    }
}

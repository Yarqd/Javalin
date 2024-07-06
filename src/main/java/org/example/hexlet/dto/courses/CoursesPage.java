package org.example.hexlet.dto.courses;

import org.example.hexlet.dto.BasePage;
import org.example.hexlet.model.Course;

import java.util.List;

public class CoursesPage extends BasePage {
    private List<Course> courses;
    private String header;
    private String term;

    public CoursesPage(List<Course> courses, String header, String term) {
        super(); // Вызов конструктора базового класса
        this.courses = courses;
        this.header = header;
        this.term = term;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public String getHeader() {
        return header;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}

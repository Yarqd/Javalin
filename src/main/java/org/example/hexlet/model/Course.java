package org.example.hexlet.model;

public class Course {
    private Long id;
    private String name;
    private String description;

    // Конструктор, принимающий два параметра
    public Course(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Геттер для id
    public Long getId() {
        return id;
    }

    // Сеттер для id
    public void setId(Long id) {
        this.id = id;
    }

    // Геттер для name
    public String getName() {
        return name;
    }

    // Сеттер для name
    public void setName(String name) {
        this.name = name;
    }

    // Геттер для description
    public String getDescription() {
        return description;
    }

    // Сеттер для description
    public void setDescription(String description) {
        this.description = description;
    }

    // Переопределение метода toString
    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

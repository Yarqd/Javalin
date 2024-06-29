package org.example.hexlet.dto.users;

import java.util.List;
import java.util.Map;
import io.javalin.validation.ValidationError;

public class BuildUserPage {
    private String name;
    private String email;
    private Map<String, List<ValidationError<Object>>> errors;

    // Пустой конструктор
    public BuildUserPage() {
    }

    // Конструктор с параметрами
    public BuildUserPage(String name, String email, Map<String, List<ValidationError<Object>>> errors) {
        this.name = name;
        this.email = email;
        this.errors = errors;
    }

    // Геттеры
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Map<String, List<ValidationError<Object>>> getErrors() {
        return errors;
    }

    // Сеттеры
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setErrors(Map<String, List<ValidationError<Object>>> errors) {
        this.errors = errors;
    }
}

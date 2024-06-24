package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;
import io.javalin.rendering.template.JavalinJte;
//import static io.javalin.rendering.template.TemplateUtil.model;

import org.example.hexlet.model.Course;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;

import java.util.ArrayList;
import java.util.List;

import static io.javalin.rendering.template.TemplateUtil.model;

public class HelloWorld {
    public static void main(String[] args) {
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });
        app.get("/", ctx -> ctx.render("index.jte"));
        app.get("/users", ctx -> ctx.result("GET /users"));
        app.post("/users", ctx -> ctx.result("POST /users"));
        app.get("/hello", ctx -> {
            String name = ctx.queryParam("name");
            if (name == null || name.isEmpty()) {
                name = "World";
            }
            ctx.result("Hello, " + name + "!");
        });
        app.get("/courses/{courseId}/lessons/{id}", ctx -> {
            var courseId = ctx.pathParam("courseId");
            var lessonId =  ctx.pathParam("id");
            if (lessonId.equals("z")) {
                throw  new NotFoundResponse("Entity with id = " + lessonId + " not found");
            }
            ctx.result("Course ID: " + courseId + " Lesson ID: " + lessonId);
        });
        app.get("/users/{id}", ctx -> {
            ctx.result("User ID: " + ctx.pathParam("id"));
        });

        List<Course> courses = createCourses();
        // Обработчик для отображения списка курсов
        app.get("/courses", ctx -> {
            var header = "Доступные курсы";
            var page = new CoursesPage(courses, header);
            ctx.render("courses/index.jte", model("page", page));
        });

        app.get("/courses/{id}", ctx -> {
            var id = Long.parseLong(ctx.pathParam("id"));
                    var course = courses.stream()
                            .filter(c -> c.getId().equals(id))
                            .findFirst()
                            .orElseThrow(() -> new io.javalin.http.NotFoundResponse("Course not found"));
            var page = new CoursePage(course, "Course Details");
            ctx.render("courses/show.jte", model("page", page));
        });

        app.start(7070);
    }
    private static List<Course> createCourses() {
        List<Course> courses = new ArrayList<>();

        Course course1 = new Course("Java Basics", "Introduction to Java programming.");
        course1.setId(1L);
        courses.add(course1);
        Course course2 = new Course("Advanced Java", "Deep dive into Java topics.");
        course2.setId(2L);
        courses.add(course2);
        Course course3 = new Course("Spring Boot", "Learn how to build applications with Spring Boot.");
        course3.setId(3L);
        courses.add(course3);
        Course course4 = new Course("Data Structures", "Understanding data structures and algorithms.");
        course4.setId(4L);
        courses.add(course4);
        Course course5 = new Course("Web Development", "Building web applications with Java.");
        course5.setId(5L);
        courses.add(course5);
        return courses;
    }
}

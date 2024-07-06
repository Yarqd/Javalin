package org.example.hexlet.controller;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.Course;

import java.util.ArrayList;
import java.util.List;

import static io.javalin.rendering.template.TemplateUtil.model;

public class CoursesController {

    private static List<Course> courses = createCourses();

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

    private static List<Course> filterCoursesByTerm(String term) {
        List<Course> filteredCourses = new ArrayList<>();
        for (Course course : courses) {
            if (course.getName().contains(term) || course.getDescription().contains(term)) {
                filteredCourses.add(course);
            }
        }
        return filteredCourses;
    }

    public static void index(Context ctx) {
        var term = ctx.queryParam("term");
        List<Course> filteredCourses;
        if (term != null && !term.isEmpty()) {
            filteredCourses = filterCoursesByTerm(term);
        } else {
            filteredCourses = courses;
        }
        var header = "Courses List";
        var page = new CoursesPage(filteredCourses, header, term);
        page.setFlash(ctx.consumeSessionAttribute("flash")); // Устанавливаем флеш-сообщение
        ctx.render("courses/index.jte", model("page", page));
    }

    public static void show(Context ctx) {
        var id = Long.parseLong(ctx.pathParam("id"));
        var course = courses.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundResponse("Course not found"));
        var page = new CoursePage(course, "Course Details");
        ctx.render("courses/show.jte", model("page", page));
    }

    public static void newForm(Context ctx) {
        ctx.render("courses/new.jte");
    }

    public static void create(Context ctx) {
        var name = ctx.formParam("name");
        var description = ctx.formParam("description");

        if (name == null || name.isEmpty() || description == null || description.isEmpty()) {
            ctx.sessionAttribute("flash", "Name and description cannot be empty");
            ctx.redirect("/courses/new");
            return;
        }

        var course = new Course(name, description);
        course.setId((long) (courses.size() + 1));
        courses.add(course);

        ctx.sessionAttribute("flash", "Course has been created!");
        ctx.redirect("/courses");
    }

    public static void delete(Context ctx) {
        var id = Long.parseLong(ctx.pathParam("id"));
        var course = courses.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundResponse("Course not found"));
        courses.remove(course);

        ctx.sessionAttribute("flash", "Course has been deleted!");
        ctx.redirect("/courses");
    }
}

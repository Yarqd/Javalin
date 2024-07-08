package org.example.hexlet.controller;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.repository.CourseRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static io.javalin.rendering.template.TemplateUtil.model;

public class CoursesController {

    public static void index(Context ctx) throws SQLException {
        var term = ctx.queryParam("term");
        List<Course> filteredCourses;
        if (term != null && !term.isEmpty()) {
            filteredCourses = filterCoursesByTerm(term);
        } else {
            filteredCourses = CourseRepository.getEntities();
        }
        var header = "Courses List";
        var page = new CoursesPage(filteredCourses, header, term);
        page.setFlash(ctx.consumeSessionAttribute("flash")); // Устанавливаем флеш-сообщение
        ctx.render("courses/index.jte", model("page", page));
    }

    public static void show(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var course = CourseRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Course with id = " + id + " not found"));
        var page = new CoursePage(course, "Course Details");
        ctx.render("courses/show.jte", model("page", page));
    }

    public static void newForm(Context ctx) {
        ctx.render("courses/new.jte");
    }

    public static void create(Context ctx) throws SQLException {
        var name = ctx.formParam("name");
        var description = ctx.formParam("description");

        if (name == null || name.isEmpty() || description == null || description.isEmpty()) {
            ctx.sessionAttribute("flash", "Name and description cannot be empty");
            ctx.redirect("/courses/new");
            return;
        }

        var course = new Course(name, description);
        CourseRepository.save(course);

        ctx.sessionAttribute("flash", "Course has been created!");
        ctx.redirect("/courses");
    }

    public static void delete(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var course = CourseRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Course with id = " + id + " not found"));
        CourseRepository.delete(id);

        ctx.sessionAttribute("flash", "Course has been deleted!");
        ctx.redirect("/courses");
    }

    private static List<Course> filterCoursesByTerm(String term) throws SQLException {
        var courses = CourseRepository.getEntities();
        List<Course> filteredCourses = new ArrayList<>();
        for (Course course : courses) {
            if (course.getName().contains(term) || course.getDescription().contains(term)) {
                filteredCourses.add(course);
            }
        }
        return filteredCourses;
    }
}

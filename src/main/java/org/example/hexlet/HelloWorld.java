package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;
import io.javalin.rendering.template.JavalinJte;
import io.javalin.validation.ValidationException;
import org.apache.commons.text.StringEscapeUtils;
import org.example.hexlet.model.Course;
import org.example.hexlet.model.User;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.dto.users.BuildUserPage;

import java.util.ArrayList;
import java.util.List;

import static io.javalin.rendering.template.TemplateUtil.model;

public class HelloWorld {
    public static void main(String[] args) {
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get(NamedRoutes.basePath(), ctx -> ctx.render("index.jte"));

        // Добавление обработчиков для пользователей и использование макетов
        List<User> users = createUsers();
        app.get(NamedRoutes.usersPath(), ctx -> {
            var page = new UsersPage(users);
            ctx.render("users/index.jte", model("page", page));
        });

        app.get(NamedRoutes.buildUserPath(), ctx -> {
            var page = new BuildUserPage();
            ctx.render("users/build.jte", model("page", page));
        });

        app.get("/users/{id}", ctx -> {
            var id = Integer.parseInt(ctx.pathParam("id"));
            var user = users.stream()
                    .filter(u -> u.getId() == id)
                    .findFirst()
                    .orElseThrow(() -> new NotFoundResponse("User not found"));
            var page = new UserPage(user);
            ctx.render("users/show.jte", model("page", page));
        });

        app.exception(NotFoundResponse.class, (e, ctx) -> {
            ctx.status(404);
            ctx.result(e.getMessage());
        });

        app.post(NamedRoutes.usersPath(), ctx -> {
                var name = ctx.formParam("name");
                var email = ctx.formParam("email");

                try {
                    var passwordConfirmation = ctx.formParam("passwordConfirmation");
                    var password = ctx.formParamAsClass("password", String.class)
                            .check(value -> value.equals(passwordConfirmation), "Пароли не совпадают")
                            .get();
                    var user = new User(createUsers().size() + 1, name, email, password);
                    users.add(user);
                    ctx.redirect(NamedRoutes.usersPath());
                } catch (ValidationException e) {
                    var page = new BuildUserPage(name, email, e.getErrors());
                    ctx.render("users/build.jte", model("page", page));
                }
        });

        app.get("/hello", ctx -> {
            String name = ctx.queryParam("name");
            if (name == null || name.isEmpty()) {
                name = "World";
            }
            ctx.result("Hello, " + name + "!");
        });

        app.get("/courses/{courseId}/lessons/{id}", ctx -> {
            var courseId = ctx.pathParam("courseId");
            var lessonId = ctx.pathParam("id");
            if (lessonId.equals("z")) {
                throw new NotFoundResponse("Entity with id = " + lessonId + " not found");
            }
            ctx.result("Course ID: " + courseId + " Lesson ID: " + lessonId);
        });

        List<Course> courses = createCourses();

        // Обработчик для отображения списка курсов
        app.get(NamedRoutes.coursesPath(), ctx -> {
            var term = ctx.queryParam("term");
            List<Course> filteredCourses;
            if (term != null && !term.isEmpty()) {
                filteredCourses = filterCoursesByTerm(term); // Фильтруем курсы по term
            } else {
                filteredCourses = courses; // Показываем все курсы
            }
            var page = new CoursesPage(filteredCourses, term);
            ctx.render("courses/index.jte", model("page", page));
        });

        app.get("/courses/{id}", ctx -> {
            var id = Long.parseLong(ctx.pathParam("id"));
            var course = courses.stream()
                    .filter(c -> c.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new NotFoundResponse("Course not found"));
            var page = new CoursePage(course, "Course Details");
            ctx.render("courses/show.jte", model("page", page));
        });

        app.get("/userss/{id}", ctx -> { // Безопасность
            var id = ctx.pathParam("id");
            var escapedId = StringEscapeUtils.escapeHtml4(id); // Экранирование
            ctx.contentType("html");
            ctx.result("<h1>" + escapedId + "</h1>"); // Вместо id используем escapedId
        });

        app.get("/userstemplate/{id}", ctx -> {
            var id = ctx.pathParam("id");
            ctx.contentType("text/html");
            ctx.render("user_template.jte", model("id", id));
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

    private static List<User> createUsers() {
        List<User> users = new ArrayList<>();

        User user1 = new User(1L, "John", "Doe", "john.doe@example.com");
        users.add(user1);
        User user2 = new User(2L, "Jane", "Smith", "jane.smith@example.com");
        users.add(user2);
        User user3 = new User(3L, "Alice", "Johnson", "alice.johnson@example.com");
        users.add(user3);
        User user4 = new User(4L, "Bob", "Brown", "bob.brown@example.com");
        users.add(user4);
        User user5 = new User(5L, "Charlie", "Davis", "charlie.davis@example.com");
        users.add(user5);
        return users;
    }

    private static List<Course> filterCoursesByTerm(String term) {
        List<Course> allCourses = createCourses();
        List<Course> filteredCourses = new ArrayList<>();
        for (Course course : allCourses) {
            if (course.getName().contains(term) || course.getDescription().contains(term)) {
                filteredCourses.add(course);
            }
        }
        return filteredCourses;
    }
}

package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import org.apache.commons.text.StringEscapeUtils;
import org.example.hexlet.controller.UsersController;
import org.example.hexlet.controller.CoursesController;
import org.example.hexlet.controller.SessionsController;
import org.example.hexlet.dto.MainPage;
import org.example.hexlet.repository.UserRepository;

import static io.javalin.rendering.template.TemplateUtil.model;

public class HelloWorld {
    public static void main(String[] args) {
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging(); // Включаем логирование
            config.fileRenderer(new JavalinJte());
        });

        // Маршрут /hello
        app.get("/hello", ctx -> {
            String name = ctx.queryParam("name");
            if (name == null || name.isEmpty()) {
                name = "World";
            }
            ctx.result("Hello, " + name + "!");
        });

        app.get("/", ctx -> {
            String visitedCookie = ctx.cookie("visited");
            System.out.println("Visited cookie: " + visitedCookie); // Лог для проверки значения куки
            boolean visited = visitedCookie != null && Boolean.parseBoolean(visitedCookie);
            String currentUser = ctx.sessionAttribute("currentUser");
            var page = new MainPage(visited, currentUser);
            ctx.render("index.jte", model("page", page));
            ctx.cookie("visited", "true");
        });

        // Инициализируем пользователей
        UserRepository.createUsers();

        // Роутинг для курсов
        app.get("/courses/new", CoursesController::newForm); // Маршрут для формы создания курса
        app.post(NamedRoutes.coursesPath(), CoursesController::create); // Маршрут для создания курсов
        app.get(NamedRoutes.coursesPath(), CoursesController::index);
        app.get("/courses/{id}", CoursesController::show);
        app.post("/courses/{id}", CoursesController::delete); // Обработка метода POST для удаления курса

        // Роутинг для пользователей
        app.get(NamedRoutes.usersPath(), UsersController::index);
        app.get(NamedRoutes.buildUserPath(), UsersController::build);
        app.get("/users/{id}", UsersController::show);
        app.post(NamedRoutes.usersPath(), UsersController::create);
        app.get("/users/{id}/edit", UsersController::edit);
        app.patch("/users/{id}", UsersController::update);
        app.delete("/users/{id}", UsersController::destroy);

        // Роутинг для сессий
        app.get(NamedRoutes.buildSessionPath(), SessionsController::build);
        app.post(NamedRoutes.sessionsPath(), SessionsController::create);
        app.delete(NamedRoutes.sessionsPath(), SessionsController::destroy);

        // Старые маршруты для безопасности
        app.get("/userss/{id}", ctx -> {
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
}

package org.example.hexlet.controller;

import io.javalin.http.Context;
import org.example.hexlet.NamedRoutes;
import org.example.hexlet.repository.UserRepository;

public class SessionsController {

    public static void build(Context ctx) {
        ctx.render("sessions/build.jte");
    }

    public static void create(Context ctx) {
        var email = ctx.formParam("email");
        var password = ctx.formParam("password");

        // Проверка пароля
        var user = UserRepository.findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            ctx.sessionAttribute("currentUser", email);
            ctx.redirect("/");
        } else {
            ctx.redirect(NamedRoutes.buildSessionPath());
        }
    }

    public static void destroy(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect("/");
    }
}

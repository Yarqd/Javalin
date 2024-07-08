package org.example.hexlet.controller;

import io.javalin.http.Context;
import org.example.hexlet.NamedRoutes;
import org.example.hexlet.repository.UserRepository;

import java.sql.SQLException;

public class SessionsController {

    public static void build(Context ctx) {
        ctx.render("sessions/build.jte");
    }

    public static void create(Context ctx) {
        var email = ctx.formParam("email");
        var password = ctx.formParam("password");

        try {
            var user = UserRepository.findByEmail(email);
            if (user.isPresent() && user.get().getPassword().equals(password)) {
                ctx.sessionAttribute("currentUser", email);
                ctx.redirect("/");
            } else {
                ctx.redirect(NamedRoutes.buildSessionPath());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            ctx.status(500).result("Internal Server Error");
        }
    }

    public static void destroy(Context ctx) {
        System.out.println("Destroy session called");
        ctx.sessionAttribute("currentUser", null);
        ctx.req().getSession().invalidate(); // Явно удаляем сессию
        ctx.redirect("/");
    }
}

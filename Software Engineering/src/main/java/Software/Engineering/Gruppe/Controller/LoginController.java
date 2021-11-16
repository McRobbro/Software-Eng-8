package Software.Engineering.Gruppe.Controller;

import io.javalin.http.Context;

public class LoginController {

    public void login(Context ctx) {
        String role = ctx.formParam("role");
        ctx.header("Set-Cookie", String.format("role=%s; Path=/", role));
        ctx.redirect("/stores");
    }
}

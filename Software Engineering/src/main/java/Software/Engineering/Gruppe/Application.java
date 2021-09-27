package Software.Engineering.Gruppe;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.plugin.rendering.vue.VueComponent;
import org.jetbrains.annotations.NotNull;

public class Application {

    public static void main(String[] args) {

        Javalin app = Javalin.create().start(7000);

        app.get("/", ctx -> {
            ctx.result("Hello World!");
        });
    }
}

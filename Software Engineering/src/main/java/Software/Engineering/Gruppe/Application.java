package Software.Engineering.Gruppe;

import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Controller.StoresController;
import Software.Engineering.Gruppe.Repository.StoreRepository;
import io.javalin.Javalin;
import io.javalin.plugin.rendering.vue.*;



public class Application {

    public static void main(String[] args) {

        String userDir = System.getProperty("user.dir");
        String databaseDir = "\\db\\group8dbftw.db";
        // init Connection to sqlite database
        SqliteDatabase sqliteDatabase = new SqliteDatabase("jdbc:sqlite:" + userDir + databaseDir);

        // init javalin web service
        Javalin app = Javalin.create(config -> {
            config.enableWebjars();
        }).start(7777);
        JavalinVue.rootDirectory(c -> c.classpathPath("/vue"));
        JavalinVue.vueVersion(c -> c.vue3("app"));
        // Init Vue Files
        app.get("/stores", new VueComponent("store-overview"));
        app.get("/stores/{slug}", new VueComponent("store-detail"));
        app.error(404, ctx -> {
            ctx.result("Generic 404 message");
        });

        // Init repos
        StoreRepository storeRepository = new StoreRepository(sqliteDatabase);
        // Init controllers
        StoresController storesController = new StoresController(storeRepository);

        //redirect to homepage
        app.before("/", ctx -> ctx.redirect("/stores"));
        // Register routes and handlers
        app.get("/home", ctx -> {
            ctx.result("Platform home page");
        });

        app.get("/api/stores", storesController::getAllStores);
        app.get("/api/stores/{slug}", storesController::getSpecificStore);

    }
}

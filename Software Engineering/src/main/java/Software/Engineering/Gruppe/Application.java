package Software.Engineering.Gruppe;

import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Controller.ProductController;
import Software.Engineering.Gruppe.Controller.StoresController;
import Software.Engineering.Gruppe.Controller.UserController;
import Software.Engineering.Gruppe.Repository.ProductRepository;
import Software.Engineering.Gruppe.Repository.StoreRepository;

import io.javalin.Javalin;
import io.javalin.core.security.RouteRole;
import io.javalin.http.Context;
import io.javalin.plugin.rendering.vue.*;

import java.util.Set;



public class Application {

    enum Role implements RouteRole {
        ANYONE, STORE_OWNER, PLATFORM_OWNER, USER;
    }


    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");
        String databaseDir = "\\db\\group8dbftw.db";
        // init Connection to sqlite database
        SqliteDatabase sqliteDatabase = new SqliteDatabase("jdbc:sqlite:" + userDir + databaseDir);
        // Init repos
        StoreRepository storeRepository = new StoreRepository(sqliteDatabase);
        ProductRepository productRepository = new ProductRepository(sqliteDatabase, storeRepository);
        // Init controllers
        UserController userController = new UserController();
        StoresController storesController = new StoresController(storeRepository);
        ProductController productController = new ProductController(productRepository);

        // init javalin web service
        Javalin app = Javalin.create(config -> {
            config.enableWebjars();
            config.accessManager((handler, ctx, permittedRoles) -> {
                if (permittedRoles.contains(Role.ANYONE) || getUserRole(ctx) == Role.PLATFORM_OWNER || permittedRoles.contains(getUserRole(ctx))) {
                    handler.handle(ctx);
                } else {
                    ctx.status(401);
                }
            });
        }).start(7777);

        JavalinVue.rootDirectory(c -> c.classpathPath("/vue"));
        JavalinVue.vueVersion(c -> c.vue3("app"));

        //redirect to login-page
        app.before("/", ctx -> ctx.redirect("/stores"));
        // Register routes and handlers
        app.get("/home", ctx -> {
            ctx.result("Platform home page");
        });

        // Init Vue Files
        app.get("/login", new VueComponent("login-page"), Role.ANYONE);
        app.get("/stores", new VueComponent("store-overview"), Role.PLATFORM_OWNER, Role.USER);
        app.get("/stores/create", new VueComponent("store-create"), Role.PLATFORM_OWNER);
        app.get("/stores/{storeSlug}", new VueComponent("store-detail"), Role.STORE_OWNER, Role.USER);
        app.get("/stores/{storeSlug}/{prodSlug}", new VueComponent("product-detail"), Role.ANYONE);




        //api
        app.post("/api/login", userController::login, Role.ANYONE);
        app.get("/api/stores", storesController::getAllStores, Role.ANYONE);
        app.post("/api/stores/create", storesController::createStore);
        app.get("/api/stores/{storeSlug}", productController::getProductsFromStore, Role.ANYONE);
        app.get("/api/stores/{storeSlug}/{prodSlug}", productController::getSpecificProduct, Role.ANYONE);
        app.get("/api/products", productController::getAllProducts, Role.ANYONE);

        app.error(404, ctx -> {
            ctx.result("Generic 404 message");
        });
    }


    public static Role getUserRole(Context ctx) {
        String role = ctx.cookie("role");
        if (role == null) {
            return Role.ANYONE;
        } else if (role.equals("PLATFORM_OWNER")) {
            return Role.PLATFORM_OWNER;
        } else if (role.equals("STORE_OWNER")) {
            return Role.STORE_OWNER;
        } else if (role.equals("USER")) {
            return Role.USER;
        } else {
            return Role.ANYONE;
        }
    }
}


//MOVED COMMENTS

// how will the path be for the products?
// the products should be gotten when entering a pages slug, as in when you get to a specific store
// you wish to view their products, so the patch to view the products and the store itself should be
// localhost:7777/stores/johansens-butikk | api/stores/{slug}
// when you open a specific product you'd want that to be
// localhost:7777/stores/johansens-butikk/Antique-Vase | api/stores/{slug}/{product-name}
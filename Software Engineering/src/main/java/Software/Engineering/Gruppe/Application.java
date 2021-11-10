package Software.Engineering.Gruppe;

import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Controller.ProductController;
import Software.Engineering.Gruppe.Controller.StoresController;
import Software.Engineering.Gruppe.Repository.ProductRepository;
import Software.Engineering.Gruppe.Repository.StoreRepository;

import io.javalin.Javalin;
import io.javalin.core.security.RouteRole;
import io.javalin.http.Context;
import io.javalin.plugin.rendering.vue.*;

public class Application {


    public static void main(String[] args) {
        /*
        enum Role implements RouteRole {
            ANYONE, LOGGED_IN;
        }

         */

        String userDir = System.getProperty("user.dir");
        String databaseDir = "\\db\\group8dbftw.db";
        // init Connection to sqlite database
        SqliteDatabase sqliteDatabase = new SqliteDatabase("jdbc:sqlite:" + userDir + databaseDir);
        // Init repos
        StoreRepository storeRepository = new StoreRepository(sqliteDatabase);
        ProductRepository productRepository = new ProductRepository(sqliteDatabase, storeRepository);
        // Init controllers
        StoresController storesController = new StoresController(storeRepository);
        ProductController productController = new ProductController(productRepository);

        // init javalin web service
        Javalin app = Javalin.create(config -> {
            config.enableWebjars();
            /*
            config.accessManager((handler, ctx, permittedRoles) -> {
                if (permittedRoles.contains(getUserRole(ctx))) {
                    handler.handle(ctx);
                } else {
                    ctx.status(401);
                }
            });

             */
        }).start(7777);
        JavalinVue.rootDirectory(c -> c.classpathPath("/vue"));
        JavalinVue.vueVersion(c -> c.vue3("app"));

        //redirect to homepage
        app.before("/", ctx -> ctx.redirect("/stores"));
        // Register routes and handlers
        app.get("/home", ctx -> {
            ctx.result("Platform home page");
        });

        // Init Vue Files
        app.get("/stores", new VueComponent("store-overview"));
        app.get("/stores/{storeSlug}", new VueComponent("store-detail"));
        app.get("/stores/{storeSlug}/{prodSlug}", new VueComponent("product-detail"));
        app.get("/login", new VueComponent("login-page"));
        app.error(404, ctx -> {
            ctx.result("Generic 404 message");
        });

        app.get("/api/stores", storesController::getAllStores);
        app.get("/api/stores/{storeSlug}", productController::getProductsFromStore);
        app.get("/api/stores/{storeSlug}/{prodSlug}", productController::getSpecificProduct);

        app.get("/api/products", productController::getAllProducts);

        //MOVED COMMENTS

        // how will the path be for the products?
        // the products should be gotten when entering a pages slug, as in when you get to a specific store
        // you wish to view their products, so the patch to view the products and the store itself should be
        // localhost:7777/stores/johansens-butikk | api/stores/{slug}
        // when you open a specific product you'd want that to be
        // localhost:7777/stores/johansens-butikk/Antique-Vase | api/stores/{slug}/{product-name}
    }
}

package Software.Engineering.Gruppe;

import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Controller.ProductController;
import Software.Engineering.Gruppe.Controller.StoresController;
import Software.Engineering.Gruppe.Repository.ProductRepository;
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
        app.get("/stores/{slug}/{productSlug}", new VueComponent("product-detail"));
        app.error(404, ctx -> {
            ctx.result("Generic 404 message");
        });
        app.get("/signup", new VueComponent("login-page"));

        // Init repos
        StoreRepository storeRepository = new StoreRepository(sqliteDatabase);
        ProductRepository productRepository = new ProductRepository(sqliteDatabase);
        // Init controllers
        StoresController storesController = new StoresController(storeRepository);
        ProductController productController = new ProductController(productRepository);

        //redirect to homepage
        app.before("/", ctx -> ctx.redirect("/stores"));
        // Register routes and handlers
        app.get("/home", ctx -> {
            ctx.result("Platform home page");
        });

        app.get("/api/stores", storesController::getAllStores);
        app.get("/api/stores/{slug}", storesController::getSpecificStore);

        // how will the path be for the products?
        // the products should be gotten when entering a pages slug, as in when you get to a specific store
        // you wish to view their products, so the patch to view the products and the store itself should be
        // localhost:7777/stores/johansens-butikk | api/stores/{slug}
        // when you open a specific product you'd want that to be
        // localhost:7777/stores/johansens-butikk/Antique-Vase | api/stores/{slug}/{product-name}
        app.get("/api/products", productController::getAllProducts);
        app.get("/api/stores/{slug}/{productSlug}", productController::getSpecificProduct);

        //test!
    }
}

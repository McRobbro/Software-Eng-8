package Software.Engineering.Gruppe;

import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Controller.*;
import Software.Engineering.Gruppe.Repository.AuctionRepository;
import Software.Engineering.Gruppe.Repository.ProductRepository;
import Software.Engineering.Gruppe.Repository.StoreRepository;

import Software.Engineering.Gruppe.Repository.UserRepository;
import io.javalin.Javalin;
import io.javalin.core.security.RouteRole;
import io.javalin.http.Context;
import io.javalin.plugin.rendering.vue.*;

import java.util.Map;


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
        UserRepository userRepository = new UserRepository(sqliteDatabase);
        AuctionRepository auctionRepository = new AuctionRepository(sqliteDatabase, storeRepository, productRepository);
        // Init controllers
        UserController userController = new UserController(userRepository);
        LoginController loginController = new LoginController();
        StoresController storesController = new StoresController(storeRepository);
        ProductController productController = new ProductController(productRepository, storeRepository);
        AuctionController auctionController = new AuctionController(auctionRepository, storeRepository, productRepository);

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

        JavalinVue.stateFunction = ctx -> Map.of("currentUser", getUserRole(ctx));
        JavalinVue.rootDirectory(c -> c.classpathPath("/vue"));
        JavalinVue.vueVersion(c -> c.vue3("app"));

        //redirect to login-page
        app.before("/", ctx -> ctx.redirect("/login"));
        // Register routes and handlers
        app.get("/home", ctx -> {
            ctx.result("Platform home page");
        });

        // Init Vue Files
        app.get("/login", new VueComponent("login-page"), Role.ANYONE);
        app.get("/users", new VueComponent("users-overview"), Role.PLATFORM_OWNER);
        app.get("/user", new VueComponent("user-detail"), Role.USER, Role.PLATFORM_OWNER, Role.STORE_OWNER);
        app.get("/users/{userUsername}", new VueComponent("users-detail"), Role.PLATFORM_OWNER);
        app.get("/stores", new VueComponent("store-overview"), Role.PLATFORM_OWNER, Role.STORE_OWNER, Role.USER);
        app.get("/stores/create", new VueComponent("store-create"), Role.PLATFORM_OWNER);
        app.get("/stores/delete", new VueComponent("store-delete"), Role.PLATFORM_OWNER);
        app.get("/stores/{storeSlug}", new VueComponent("store-detail"), Role.STORE_OWNER, Role.USER);
        app.get("/stores/{storeSlug}/update", new VueComponent("store-update"), Role.STORE_OWNER);
        app.get("/stores/{storeSlug}/createProduct", new VueComponent("product-create"), Role.STORE_OWNER);
        app.get("/stores/{storeSlug}/deleteProduct", new VueComponent("product-delete"), Role.STORE_OWNER);
        app.get("/stores/{storeSlug}/{prodSlug}", new VueComponent("product-detail"), Role.ANYONE);
        app.get("/stores/{storeSlug}/{prodSlug}/updateProduct", new VueComponent("product-update"), Role.STORE_OWNER);

        //api
        app.post("/api/login", loginController::login, Role.ANYONE);
        app.post("api/user/{userId}/update", userController::updateUser, Role.ANYONE);
        app.get("api/users", userController::getUsers, Role.ANYONE);
        app.get("api/users/{userUsername}", userController::getSpecificUserByUsername, Role.ANYONE);
        //app.post("api/users/{userUsername}/delete", userController::deleteUser, Role.ANYONE);
        app.get("/api/stores", storesController::getAllStores, Role.ANYONE);
        app.post("/api/stores/create", storesController::createStore, Role.ANYONE);
        app.post("/api/stores/delete", storesController::deleteStore, Role.ANYONE);
        app.get("/api/stores/{storeSlug}", productController::getProductsFromStore, Role.ANYONE);
        app.get("/api/stores/{storeSlug}/auctions", auctionController::getAllAuctionsFromStore, Role.ANYONE);
        app.post("/api/stores/{storeSlug}/update", storesController::updateStore, Role.ANYONE);
        app.post("/api/stores/{storeSlug}/createProduct", productController::createProduct, Role.ANYONE);
        app.post("/api/stores/{storeSlug}/deleteProduct", productController::deleteProduct, Role.ANYONE);
        app.get("/api/stores/{storeSlug}/{prodSlug}", productController::getSpecificProduct, Role.ANYONE);
        app.post("/api/stores/{storeSlug}/{prodSlug}/updateProduct", productController::updateProduct, Role.ANYONE);
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
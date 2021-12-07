package Software.Engineering.Gruppe;

import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Controller.*;
import Software.Engineering.Gruppe.Repository.*;

import io.javalin.Javalin;
import io.javalin.core.security.RouteRole;
import io.javalin.http.Context;
import io.javalin.plugin.rendering.vue.*;

import java.util.Map;


public class Application {

    enum Role implements RouteRole {
        Uregistrert_bruker, Butikkeier, Administrator, Registrert_bruker;
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
        BidRepository bidRepository = new BidRepository(sqliteDatabase, userRepository, auctionRepository);
        OrderRepository orderRepository = new OrderRepository(sqliteDatabase, userRepository, storeRepository);
        // Init controllers
        UserController userController = new UserController(userRepository);
        LoginController loginController = new LoginController();
        StoresController storesController = new StoresController(storeRepository);
        ProductController productController = new ProductController(productRepository, storeRepository);
        AuctionController auctionController = new AuctionController(auctionRepository, storeRepository, productRepository);
        BidController bidController = new BidController(bidRepository, userRepository, auctionRepository);
        OrderController orderController = new OrderController(orderRepository, storeRepository, productRepository, userRepository);

        // init javalin web service
        Javalin app = Javalin.create(config -> {
            config.enableWebjars();
            config.accessManager((handler, ctx, permittedRoles) -> {
                if (permittedRoles.contains(Role.Uregistrert_bruker) || getUserRole(ctx) == Role.Administrator || permittedRoles.contains(getUserRole(ctx))) {
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
            ctx.result("Plattformens hjemmeside");
        });


        // Init Vue Files
        app.get("/login", new VueComponent("login-page"), Role.Uregistrert_bruker);
        app.get("/users", new VueComponent("users-overview"), Role.Administrator);
        app.get("/user", new VueComponent("user-detail"), Role.Registrert_bruker, Role.Administrator, Role.Butikkeier);
        app.get("/users/{userUsername}", new VueComponent("users-detail"), Role.Administrator);
        app.get("/stores", new VueComponent("store-overview"), Role.Administrator, Role.Butikkeier, Role.Registrert_bruker, Role.Uregistrert_bruker);
        app.get("/stores/create", new VueComponent("store-create"), Role.Administrator);
        app.get("/stores/delete", new VueComponent("store-delete"), Role.Administrator);
        app.get("/stores/{storeSlug}", new VueComponent("store-detail"), Role.Butikkeier, Role.Registrert_bruker);
        app.get("/stores/{storeSlug}/update", new VueComponent("store-update"), Role.Butikkeier);
        app.get("/stores/{storeSlug}/createProduct", new VueComponent("product-create"), Role.Butikkeier);
        app.get("/stores/{storeSlug}/deleteProduct", new VueComponent("product-delete"), Role.Butikkeier);
        app.get("/stores/{storeSlug}/createAuction", new VueComponent("auction-create"), Role.Butikkeier);
        app.get("/stores/{storeSlug}/{prodSlug}", new VueComponent("product-detail"), Role.Uregistrert_bruker);
        app.get("/stores/{storeSlug}/auctions/{auctionProd}", new VueComponent("product-auction"), Role.Administrator, Role.Butikkeier, Role.Registrert_bruker);
        app.get("/stores/{storeSlug}/{prodSlug}/updateProduct", new VueComponent("product-update"), Role.Butikkeier);

        //api
        app.post("/api/login", loginController::login, Role.Uregistrert_bruker);
        app.post("api/user/{userId}/update", userController::updateUser, Role.Uregistrert_bruker);
        app.get("api/users", userController::getUsers, Role.Uregistrert_bruker);
        app.get("api/users/{userUsername}", userController::getSpecificUserByUsername, Role.Uregistrert_bruker);
        //app.post("api/users/{userUsername}/delete", userController::deleteUser, Role.ANYONE);
        app.get("/api/stores", storesController::getAllStores, Role.Uregistrert_bruker);
        app.post("/api/stores/create", storesController::createStore, Role.Uregistrert_bruker);
        app.post("/api/stores/delete", storesController::deleteStore, Role.Uregistrert_bruker);
        app.get("/api/stores/{storeSlug}", productController::getProductsFromStore, Role.Uregistrert_bruker);
        app.get("/api/stores/{storeSlug}/auctions", auctionController::getAllAuctionsFromStore, Role.Uregistrert_bruker);
        app.post("/api/stores/{storeSlug}/update", storesController::updateStore, Role.Uregistrert_bruker);
        app.post("/api/stores/{storeSlug}/createProduct", productController::createProduct, Role.Uregistrert_bruker);
        app.post("/api/stores/{storeSlug}/deleteProduct", productController::deleteProduct, Role.Uregistrert_bruker);
        app.post("/api/stores/{storeSlug}/createAuction", auctionController::createAuction, Role.Uregistrert_bruker);
        app.get("/api/stores/{storeSlug}/{prodSlug}", productController::getSpecificProduct, Role.Uregistrert_bruker);
        app.post("/api/stores/{storeSlug}/{prodSlug}/purchase", orderController::orderTheProduct, Role.Uregistrert_bruker);
        app.get("/api/stores/{storeSlug}/auctions/{auctionProd}", auctionController::getSpecificAuction, Role.Uregistrert_bruker);
        app.get("/api/stores/{storeSlug}/auctions/{auctionProd}/currentHighestBid", bidController::getCurrentHighestBid, Role.Uregistrert_bruker);
        app.post("/api/stores/{storeSlug}/auctions/{auctionProd}/createBid", bidController::createBid, Role.Uregistrert_bruker);
        app.post("/api/stores/{storeSlug}/{prodSlug}/updateProduct", productController::updateProduct, Role.Uregistrert_bruker);
        app.get("/api/products", productController::getAllProducts, Role.Uregistrert_bruker);

        app.error(404, ctx -> {
            ctx.result("Generic 404 message");
        });
    }

    public static Role getUserRole(Context ctx) {
        String role = ctx.cookie("role");
        if (role == null) {
            return Role.Uregistrert_bruker;
        } else if (role.equals("Administrator")) {
            return Role.Administrator;
        } else if (role.equals("Butikkeier")) {
            return Role.Butikkeier;
        } else if (role.equals("Registrert bruker")) {
            return Role.Registrert_bruker;
        } else {
            return Role.Uregistrert_bruker;
        }
    }
}
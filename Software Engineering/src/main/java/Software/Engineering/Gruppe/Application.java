package Software.Engineering.Gruppe;

import Software.Engineering.Gruppe.Config.SQLDatabase;
import Software.Engineering.Gruppe.Controller.StoresController;
import Software.Engineering.Gruppe.Repository.StoreRepository;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class Application {

    public static void main(String[] args) {

        SQLDatabase SQLdatabase = new SQLDatabase(
                "jdbc:mysql://34.88.134.36:3306/soe_group_8",
            "root",
            "group8ftw"
        );

        // Init repos
        StoreRepository storeRepository = new StoreRepository(SQLdatabase);

        // Init controllers
        StoresController storesController = new StoresController(storeRepository);


        Javalin app = Javalin.create().start(7000);

        // Register routes and handlers
        app.get("/", ctx -> {
            ctx.result("Platform home page");
        });

        app.get("/stores", storesController::getAllStores);

    }
}

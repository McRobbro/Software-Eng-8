package Software.Engineering.Gruppe;

import Software.Engineering.Gruppe.Config.SQLDatabase;
import Software.Engineering.Gruppe.Controller.StoresController;
import Software.Engineering.Gruppe.Repository.StoreRepository;
import io.javalin.Javalin;

public class Application {

    public static void main(String[] args) {

        SQLDatabase SQLdatabase = new SQLDatabase("jdbc:mysql://34.88.134.36:3306/soe_group_8",
                                                "root",
                                                "group8ftw");
        SQLdatabase.connectDatabase();


        StoreRepository storeRepository = new StoreRepository(SQLdatabase);
        StoresController storesController = new StoresController(storeRepository);

        Javalin app = Javalin.create().start(7000);

        app.get("/", ctx -> {
            ctx.result("Hello World!");
        });



    }
}

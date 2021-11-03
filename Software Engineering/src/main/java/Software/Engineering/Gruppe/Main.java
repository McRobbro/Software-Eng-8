package Software.Engineering.Gruppe;
import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Repository.OrderRepository;
import Software.Engineering.Gruppe.Repository.ProductRepository;
import Software.Engineering.Gruppe.Repository.StoreRepository;
import Software.Engineering.Gruppe.Repository.UserRepository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;


public class Main {

    public static void main(String[] args) throws IOException {

        String userDir = System.getProperty("user.dir");
        String databaseDir = "\\db\\group8dbftw.db";
        SqliteDatabase sqliteDatabase = new SqliteDatabase("jdbc:sqlite:" + userDir + databaseDir);

        StoreRepository storeRepository = new StoreRepository(sqliteDatabase);
        ProductRepository productRepository = new ProductRepository(sqliteDatabase);
        UserRepository userRepository = new UserRepository(sqliteDatabase);
        OrderRepository orderRepository = new OrderRepository(sqliteDatabase);

        System.out.println(storeRepository.getAllStores());
        System.out.println(storeRepository.getSpecificStoreBySlug("Fredriks-butikk").getSlug());
        System.out.println(storeRepository.getSpecificStoreBySlug("johansens-butikk").getSlug());
        System.out.println(storeRepository.getSpecificStoreBySlug("philips-butikk").getSlug());

        // System.out.println(storeRepository.createStore("Marius-butikk", "Marius", "null","lorem ipsum"));
        // System.out.println(storeRepository.updateStore(5,"Andreas-butikk", "Andreas", "null", "lorem ipsum"));
        // System.out.println(storeRepository.deleteStore("Marcus-butikk"));

        //Cornelia is checking functionality
        //System.out.println(storeRepository.createStore("Heidi-butikk", "Heidi", "null","lorem ipsum"));
        //System.out.println(storeRepository.createStore("Zofie-butikk", "Zofie", "null","vintagevarer"));
        //System.out.println(storeRepository.getSpecificStoreBySlug("Heidi-butikk"));
        //System.out.println(storeRepository.updateStore(11, "Zofies-butikk", "Zofie", "null", "vintagevarer"));
        //System.out.println(storeRepository.getSpecificStoreBySlug("Zofies-butikk").getSlug());

        //System.out.println(storeRepository.createStore("Maia-butikk", "Maia", "null","lorem ipsum"));
        System.out.println(storeRepository.getAllStores());

        //creating products
       // System.out.println(productRepository.createProduct("LUX-taklampe", "LUX taklampe", "null", "lorem ipsum", "Belysning"));
        //System.out.println(productRepository.getAllProducts());
        //System.out.println(storeRepository.getSpecificStoreBySlug("philips-butikk").addProductBySlug("LUX-taklampe"));
        System.out.println("\nDivider\n");
        System.out.println(productRepository.getSpecificProduct("johansens-butikk", "LUX-taklampe"));
        System.out.println(productRepository.getSpecificProduct("Fredriks-butikk", "LUX-taklampe"));
        System.out.println(productRepository.getSpecificProduct("philips-butikk", "Da-Vinci-Barglob-modell-102"));


        System.out.println(userRepository.getSpecificUser(100));

        System.out.println(orderRepository.createOrder(LocalDate.now(), userRepository.getSpecificUser(100),
                storeRepository.getSpecificStoreBySlug("philips-butikk")));

    }
}

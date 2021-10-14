package Software.Engineering.Gruppe;
import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Repository.StoreRepository;

import java.io.IOException;

import static Software.Engineering.Gruppe.Config.SqliteDatabase.connect;


public class Main {

    public static void main(String[] args) throws IOException {


        String userDir = System.getProperty("user.dir");
        String databaseDir = "\\db\\group8dbftw.db";
        SqliteDatabase sqliteDatabase = new SqliteDatabase("jdbc:sqlite:" + userDir + databaseDir);


        System.out.println(userDir);

        StoreRepository storeRepository = new StoreRepository(sqliteDatabase);

        System.out.println(storeRepository.getAllStores());
        System.out.println(storeRepository.getSpecificStoreBySlug("Fredriks-butikk"));

        System.out.println(storeRepository.getSpecificStoreBySlug("Fredriks-butikk").getSlug());


        System.out.println(storeRepository.getSpecificStoreBySlug("Fredriks-butikk").getSlug());
        System.out.println(storeRepository.getSpecificStoreBySlug("johansens-butikk").getSlug());
        System.out.println(storeRepository.getSpecificStoreBySlug("philips-butikk").getSlug());



        System.out.println(connect());



      // System.out.println(storeRepository.createStore("Marius-butikk", "Marius", "null","lorem ipsum"));

      //  System.out.println(storeRepository.updateStore(5,"Andreas-butikk", "Andreas", "null", "lorem ipsum"));

      //  System.out.println(storeRepository.deleteStore("Marius-butikk"));



        /*
        String url = "jdbc:mysql://34.88.134.36:3306/soe_group_8";
        String user = "root";
        String password = "group8ftw";

        try  (Connection connection = DriverManager.getConnection(url, user, password)) {

            System.out.println("Connected to SQL database: " + url);
            String query = "SELECT * FROM shop";

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                int shopID = resultSet.getInt("shopID");
                String shopName = resultSet.getString("shopName");
                String description = resultSet.getString("description");
                Store oneShop = new Store(shopID, shopName, description);

                System.out.println(oneShop);
            }

            Scanner sc = new Scanner(System.in);

            String scannedUsername = sc.nextLine();
            String scannedPassword = sc.nextLine();

            User user1 = new User(scannedUsername, scannedPassword);

            String query2 = " insert into Users (user_username, user_password)"
                    + " values (?, ?)";

            PreparedStatement preparesStmt = connection.prepareStatement(query2);
            preparesStmt.setString(1, user1.getUsername());
            preparesStmt.setString(2, user1.getPassword());

            preparesStmt.execute();


        }
        catch (Exception e) {
            System.out.println(e);
        }

         */






    }
}

package Software.Engineering.Gruppe;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {

        System.out.println("hello world");
        System.out.println("Testing Commits");
        System.out.println("hello world");
        System.out.println("Testing Commits again!");
        System.out.println("CK tester");


        String url = "jdbc:mysql://34.88.134.36:3306/soe_group_8";
        String user = "root";
        String password = "group8ftw";

        try  (Connection connection = DriverManager.getConnection(url, user, password)) {

            System.out.println("Connected to SQL database: " + url);
            String query = "SELECT * FROM shop";

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                int shopID = resultSet.getInt("storeID");
                String shopName = resultSet.getString("storeID");
                String description = resultSet.getString("description");
                Shop oneShop = new Shop(shopID, shopName, description);

                System.out.println(oneShop);
            }


        } catch (Exception e) {
            System.out.println(e);
        }


    }
}

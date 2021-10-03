package Software.Engineering.Gruppe;
import Software.Engineering.Gruppe.Model.*;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

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
                Shop oneShop = new Shop(shopID, shopName, description);

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


    }
}

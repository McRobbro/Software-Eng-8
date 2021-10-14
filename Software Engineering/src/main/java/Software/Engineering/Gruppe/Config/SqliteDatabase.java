package Software.Engineering.Gruppe.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteDatabase {

    private final String url;

    public SqliteDatabase(String url) {
        this.url = url;
    }


    public static Boolean connect() {
        Connection conn = null;
        String userDir = System.getProperty("user.dir");
        System.out.println(userDir);

        String url = "jdbc:sqlite:C:\\Users\\phili\\code\\SOE2021-gruppeOppgave-prototype\\Software-Eng-8\\db\\group8dbftw.db";

        System.out.println(url);

        try {

            conn = DriverManager.getConnection(url);
            System.out.println(conn);
            System.out.println("Connection to SQLite has been established.");
            return true;



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;

    }

    public Connection getConnection() throws SQLException {

        return DriverManager.getConnection(url);

    }



}

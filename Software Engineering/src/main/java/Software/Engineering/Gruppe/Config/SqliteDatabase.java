package Software.Engineering.Gruppe.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteDatabase {

    public static class Connect {

        public static void connect() {
            Connection conn = null;
            try {

                // url to the database
                String url = "jdbc:sqlite:C:\\Users\\sqlite\\db\\group8dbftw.db";

                conn = DriverManager.getConnection(url);

                System.out.println("Connection to SQLite has been established.");

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                try {

                    if (conn != null) {
                        conn.close();
                    }

                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

    }

}

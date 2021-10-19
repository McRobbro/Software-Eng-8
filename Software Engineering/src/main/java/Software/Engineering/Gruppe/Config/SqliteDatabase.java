package Software.Engineering.Gruppe.Config;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteDatabase {

    private final String url;

    public SqliteDatabase(String url) {
        this.url = url;
    }


    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }

    public static Boolean connect() {
        Connection conn = null;
        Path userDir = Paths.get(System.getProperty("user.dir")).getParent();
        String databaseDir = "\\db\\group8dbftw.db";
        String url = "jdbc:sqlite:" + userDir + databaseDir;

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



}

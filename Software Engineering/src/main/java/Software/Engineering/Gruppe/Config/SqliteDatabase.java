package Software.Engineering.Gruppe.Config;

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
}

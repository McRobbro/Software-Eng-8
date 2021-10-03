package Software.Engineering.Gruppe.Config;

import java.sql.*;

public class SQLDatabase {

    private final String url;
    private final String username;
    private final String password;

    public SQLDatabase(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}

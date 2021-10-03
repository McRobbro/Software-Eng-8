package Software.Engineering.Gruppe.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLDatabase {

    private String url;
    private String username;
    private final String password;

    public SQLDatabase(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void connectDatabase() {
        System.out.println("Connecting to database");
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Success");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
}

package Software.Engineering.Gruppe.Repository;

import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepository implements UserInterface {

    private final SqliteDatabase database;
    public UserRepository(SqliteDatabase database) {this.database = database;}

    @Override
    public User createUser(String email, String username, String password) {
        User user = new User(email, username, password);
        String query = "INSERT INTO user(email, username, password) VALUES(?,?,?,?,?)";

        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
            return new User(email, username, password);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;

    }



    @Override
    public User getSpecificUser(int userId, String email, String username, String password) {
        return null;
    }

    @Override
    public boolean deleteUser(int userId) {
        return false;
    }
}

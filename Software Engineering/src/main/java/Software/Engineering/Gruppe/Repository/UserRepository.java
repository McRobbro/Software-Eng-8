package Software.Engineering.Gruppe.Repository;

import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository implements UserInterface {

    private final SqliteDatabase database;
    public UserRepository(SqliteDatabase database) {this.database = database;}

    @Override
    public User createUser(String email, String username, String password) {
        String query = "INSERT INTO user(email, username, password) VALUES(?,?,?)";

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


    public User createUser(int userId, String email, String username, String password) {
        String query = "INSERT INTO user(userId, email, username, password) VALUES(?,?,?,?)";

        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, password);
            preparedStatement.executeUpdate();
            return new User(userId, email, username, password);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    @Override
    public User getSpecificUser(int userId) {
        User specificUser = null;
        String query = "select * from user where userId = ?";
        try (Connection connection = database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, String.valueOf(userId));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int user = resultSet.getInt("userId");
                String email = resultSet.getString("email");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                specificUser = new User(user, email, username, password);
            }

            return specificUser;
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return null;
    }

        @Override
        public boolean deleteUser (int userId){
            String query = "DELETE FROM user where userId = ?";
            try {Connection connection = database.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, String.valueOf(userId));
                preparedStatement.executeUpdate();
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return false;
        }
    }
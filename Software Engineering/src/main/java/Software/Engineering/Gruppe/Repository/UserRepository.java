package Software.Engineering.Gruppe.Repository;

import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Model.User;
import Software.Engineering.Gruppe.Repository.interfaces.UserInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements UserInterface {

    private final SqliteDatabase database;

    public UserRepository(SqliteDatabase database) {this.database = database;}

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * from user";

        try (Connection cn = database.getConnection()) {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                int userId = rs.getInt("userId");
                String email = rs.getString("email");
                String username = rs.getString("username");
                String password = rs.getString("password");
                userList.add(new User(userId, email, username, password));
            }
            return userList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

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
        String query = "INSERT INTO user(user" +
                "Id, email, username, password) VALUES(?,?,?,?)";

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
    public User getSpecificUserByUsername(String userUsername) {
        User specificUser = null;
        String query = "SELECT * FROM user WHERE username = ?";
        try (Connection connection = database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userUsername);
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
                if(getSpecificUser(userId)==null) {
                    return true;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return false;
        }

        @Override
        public User updateUser (String userId, String email, String username, String password) {
            String query = "UPDATE user SET email = ?, " + "username = ?, " + "password = ?" + "WHERE userId = ?";

            try (Connection cn = database.getConnection();
                 PreparedStatement st = cn.prepareStatement(query)) {
                st.setString(1, email);
                st.setString(2, username);
                st.setString(3, password);
                st.setString(4, userId);
                st.executeUpdate();
                return new User(email, username, password);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return null;
        }
    }
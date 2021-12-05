package Software.Engineering.Gruppe.Repository;

import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Model.Order;
import Software.Engineering.Gruppe.Model.Store;
import Software.Engineering.Gruppe.Model.User;
import Software.Engineering.Gruppe.Repository.interfaces.OrderInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderRepository implements OrderInterface {

    private final SqliteDatabase database;
    UserRepository userRepository;
    StoreRepository storeRepository;

    public OrderRepository(SqliteDatabase database, UserRepository userRepository, StoreRepository storeRepository) {
        this.database = database;
        this.userRepository = userRepository;
        this.storeRepository = storeRepository;
    }

    /*
    @Override
    public List<Order> getAllOrders() {
        return null;
    }
    */

    /*@Override
    public Order getSpecificOrderBySlug(String SLUG) {
        return null;
    }*/

    @Override
    public Order createOrder(LocalDateTime orderDate, User userId, Store storeId) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String query = "INSERT INTO 'order' (orderDate, userId, storeId) VALUES(?,?,?)";

        try (Connection connection = database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, orderDate.format(format));
            preparedStatement.setInt(2, userId.getUserId());
            preparedStatement.setInt(3, storeId.getStoreId());
            preparedStatement.executeUpdate();
            return new Order(orderDate, userId, storeId);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    
    public Order createOrder(int orderId, LocalDateTime orderDate, User userId, Store storeId) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String query = "INSERT INTO 'order' (orderId, orderDate, userId, storeId) VALUES(?,?,?,?)";

        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, orderId);
            preparedStatement.setString(2, orderDate.format(format));
            preparedStatement.setInt(3, userId.getUserId());
            preparedStatement.setInt(4, storeId.getStoreId());
            preparedStatement.executeUpdate();
            return new Order(orderId, orderDate, userId, storeId);
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Order getOrderById(int orderId) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        Order specificOrder = null;
        String query = "SELECT * FROM 'order' WHERE orderId = ?";

        try (Connection cn = database.getConnection()) {
            PreparedStatement st = cn.prepareStatement(query);
            st.setInt(1, orderId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("orderId");
                String orderDate = rs.getString("orderDate");
                LocalDateTime ldtOTime = LocalDateTime.parse(orderDate, format);
                int userId = rs.getInt("userId");
                User user = userRepository.getSpecificUser(userId);
                int storeId = rs.getInt("storeId");
                Store store = storeRepository.getSpecificStoreById(storeId);
                specificOrder = new Order(id, ldtOTime, user, store);
            }
            return specificOrder;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean deleteOrder(int orderId) {
        String query = "DELETE FROM 'order' WHERE orderId = ?";
        try (Connection cn = database.getConnection();
            PreparedStatement st = cn.prepareStatement(query)) {
            st.setInt(1, orderId);
            st.executeUpdate();
            if(getOrderById(orderId) == null) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }


}


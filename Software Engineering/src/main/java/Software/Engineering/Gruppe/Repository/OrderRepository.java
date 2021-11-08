package Software.Engineering.Gruppe.Repository;

import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Model.Order;
import Software.Engineering.Gruppe.Model.Store;
import Software.Engineering.Gruppe.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class OrderRepository implements OrderInterface{

    private final SqliteDatabase database;
    public OrderRepository(SqliteDatabase database) {this.database = database;}

    @Override
    public List<Order> getAllOrders() {
        return null;
    }

    /*@Override
    public Order getSpecificOrderBySlug(String SLUG) {
        return null;
    }*/

    @Override
    public Order createOrder(LocalDateTime orderDate, User userId, Store storeId) {
        String query = "INSERT INTO order (orderDate, userId, storeId) VALUES(?,?,?)";

        try (Connection connection = database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            //preparedStatement.setInt(1, orderId);
            //preparedStatement.setInt(1, orderDate.getYear());
            preparedStatement.setInt(2, userId.getUserId());
            preparedStatement.setInt(3, storeId.getStoreId());
            preparedStatement.executeUpdate();
            return new Order(orderDate, userId, storeId);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Order getOrderById(int id) {
        return null;
    }


    @Override
    public boolean deleteOrder(String slug) {
        return false;
    }


}


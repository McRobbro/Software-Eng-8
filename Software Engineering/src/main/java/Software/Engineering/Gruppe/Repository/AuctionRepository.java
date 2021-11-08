package Software.Engineering.Gruppe.Repository;

import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Model.Auction;
import Software.Engineering.Gruppe.Model.Product;
import Software.Engineering.Gruppe.Model.Store;
import Software.Engineering.Gruppe.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuctionRepository implements AuctionInterface {

    private final SqliteDatabase database;

    public AuctionRepository(SqliteDatabase database) {
        this.database = database;
    }

    @Override
    public Auction createAuction(Store store, Product product, LocalDateTime startTime, LocalDateTime endTime) {
        String query = "INSERT INTO auction(storeId, productId, start_time, end_time) VALUES(?,?,?,?)";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, store.getStoreId());
            preparedStatement.setInt(2, product.getProductId());
            preparedStatement.setString(3, startTime.format(format));
            preparedStatement.setString(4, endTime.format(format));
            preparedStatement.executeUpdate();
            return new Auction(store, product, startTime, endTime);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Auction getAuctionById(int auctionId) {
        Auction spesificAuction = null;
        String query="select * from auction where auctionId = ?";
        try (Connection connection = database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, auctionId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return null;
    }

}

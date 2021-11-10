package Software.Engineering.Gruppe.Repository;

import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Model.Auction;
import Software.Engineering.Gruppe.Model.Bid;
import Software.Engineering.Gruppe.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BidRepository implements BidInterface {

    private final SqliteDatabase database;

    public BidRepository(SqliteDatabase database) {
        this.database = database;
    }


    @Override
    public Bid makeBid(User user, Auction auction, double amount) {
        String query = "INSERT INTO bid(userId, auctionId, amount) VALUES(?,?,?)";

        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.setInt(2, auction.getAuctionId());
            preparedStatement.setDouble(3, amount);
            preparedStatement.executeUpdate();
            return new Bid(user, auction, amount);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


}

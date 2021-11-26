package Software.Engineering.Gruppe.Repository;

import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Model.Auction;
import Software.Engineering.Gruppe.Model.Bid;
import Software.Engineering.Gruppe.Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BidRepository implements BidInterface {

    private final SqliteDatabase database;
    UserRepository userRepository;
    AuctionRepository auctionRepository;

    public BidRepository(SqliteDatabase database, UserRepository userRepository, AuctionRepository auctionRepository) {
        this.database = database;
        this.userRepository = userRepository;
        this.auctionRepository = auctionRepository;
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


    @Override
    public List<Bid> getBidFromAuctionId(int id) {
        List<Bid> BidList = new ArrayList<>();
        String query = "SELECT * FROM bid WHERE auctionId = ?";

        try (Connection cn = database.getConnection()) {
            PreparedStatement st = cn.prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int bidId = rs.getInt("bidId");
                int userId = rs.getInt("userId");
                User user = userRepository.getSpecificUser(userId);
                int auctionId = rs.getInt("auctionId");
                Auction auction = auctionRepository.getAuctionById(auctionId);
                double amount = rs.getDouble("amount");
                BidList.add(new Bid(bidId, user, auction, amount));

            }
            return BidList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    @Override
    public Bid getWinner(int id) {
        Auction currentAuction = auctionRepository.getAuctionById(id);
        if(currentAuction.getAuctionTimeDurationInMin() <= 0) {
            List<Bid> BidList = getBidFromAuctionId(id);
            if (BidList.size() == 0) {
                return null;
            }
            Bid highestBid = BidList.get(0);
            for (Bid newHighestBid : BidList) {
                if (newHighestBid.getAmount() > highestBid.getAmount()) {
                    highestBid = newHighestBid;
                }
                return highestBid;
            }
        }
        return null;
    }

}
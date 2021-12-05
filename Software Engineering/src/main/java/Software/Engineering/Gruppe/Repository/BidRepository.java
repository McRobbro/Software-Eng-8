package Software.Engineering.Gruppe.Repository;

import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Model.*;
import Software.Engineering.Gruppe.Repository.interfaces.BidInterface;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
        if(amount < auction.getStartPrice()) {
            return null;
        }
        if(amount > currentHighestBidOnAuction(auction.getAuctionId()) && auction.getTimeDurationToAuctionEnd() > 0 && LocalDateTime.now().isAfter(auction.getStartTime())) {
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
        }
        return null;
    }

    @Override
    public Bid makeBid(int bidId, User user, Auction auction, double amount) {
        if(amount < auction.getStartPrice()) {
            return null;
        }
        if(amount > currentHighestBidOnAuction(auction.getAuctionId()) && auction.getTimeDurationToAuctionEnd() > 0 && LocalDateTime.now().isAfter(auction.getStartTime())) {
            String query = "INSERT INTO bid(bidId, userId, auctionId, amount) VALUES(?,?,?,?)";
            try (Connection connection = database.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, bidId);
                preparedStatement.setInt(2, user.getUserId());
                preparedStatement.setInt(3, auction.getAuctionId());
                preparedStatement.setDouble(4, amount);
                preparedStatement.executeUpdate();
                return new Bid(bidId, user, auction, amount);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }


    public double currentHighestBidOnAuction(int auctionId) {
        List<Bid> auctionBidList = getAllBidsFromSpecificAuction(auctionId);
        if(auctionBidList.size() <= 0) {
            return 0;
        }
        auctionBidList.sort((bid1, bid2) -> {
            if (bid1.getAmount() > bid2.getAmount()) {
                return -1;
            } else if (bid1.getAmount() < bid2.getAmount()) {
                return 1;
            }
            return 0;
        });
        return auctionBidList.get(0).getAmount();
    }


    @Override
    public List<Bid> getAllBidsFromSpecificAuction(int id) {
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
    public Bid getWinner(int auctionId) {
        Auction currentAuction = auctionRepository.getAuctionById(auctionId);
        if(currentAuction.getTimeDurationToAuctionEnd() <= 0) {
            List<Bid> BidList = getAllBidsFromSpecificAuction(auctionId);
            if (BidList.size() == 0) {
                return null;
            }
            Bid highestBid = BidList.get(0);
            for (Bid newHighestBid : BidList) {
                if (newHighestBid.getAmount() > highestBid.getAmount()) {
                    highestBid = newHighestBid;
                }

            }
            return highestBid;
        }
        return null;
    }


    @Override
    public Boolean deleteBid(int auctionId) {
        String query = "DELETE FROM bid WHERE bidId = ?";
        try (Connection cn = database.getConnection();
             PreparedStatement st = cn.prepareStatement(query)) {
            st.setInt(1, auctionId);
            st.executeUpdate();
            if(getAllBidsFromSpecificAuction(auctionId).isEmpty()) {
                return true;
            }
            else {
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }


    @Override
    public Bid getSpecificBidById(int bidId) {
        Bid specificBid = null;
        String query = "select * from bid where bidId = ?";
        try (Connection connection = database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, bidId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            int bid = resultSet.getInt("bidId");
            int userId = resultSet.getInt("userId");
            User user = userRepository.getSpecificUser(userId);
            int auctionId = resultSet.getInt("auctionId");
            Auction auction = auctionRepository.getAuctionById(auctionId);
            double amount = resultSet.getDouble("amount");
            specificBid = new Bid(bid, user, auction, amount);
            return specificBid;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return null;
    }
}
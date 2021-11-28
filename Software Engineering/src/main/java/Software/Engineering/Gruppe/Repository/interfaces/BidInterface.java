package Software.Engineering.Gruppe.Repository.interfaces;

import Software.Engineering.Gruppe.Model.Auction;
import Software.Engineering.Gruppe.Model.Bid;
import Software.Engineering.Gruppe.Model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface BidInterface {

    Bid makeBid(User user, Auction auction, double amount);

    List<Bid> getBidFromAuctionId(int id);

    Bid getWinner(int id);

}

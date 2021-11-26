package Software.Engineering.Gruppe.Model;


public class Bid {
    private int BidId;
    private User userId;
    private Auction auctionId;
    private double amount;

    public Bid(int bidId, User userId, Auction auctionId, double amount) {
        this.BidId = bidId;
        this.userId = userId;
        this.auctionId = auctionId;
        this.amount = amount;
    }

    public Bid(User userId, Auction auctionId, double amount) {
        this.userId = userId;
        this.auctionId = auctionId;
        this.amount = amount;
    }

    public int getBidId() {
        return BidId;
    }

    public void setBidId(int bidId) {
        BidId = bidId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Auction getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Auction auctionId) {
        this.auctionId = auctionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "BidId=" + BidId +
                ", userId=" + userId +
                ", auctionId=" + auctionId +
                ", amount=" + amount +
                '}';
    }

}

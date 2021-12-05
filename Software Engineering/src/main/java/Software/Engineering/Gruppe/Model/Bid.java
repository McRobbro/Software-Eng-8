package Software.Engineering.Gruppe.Model;


public class Bid {
    private int bidId;
    private User userId;
    private Auction auctionId;
    private double amount;

    public Bid(int bidId, User userId, Auction auctionId, double amount) {
        this.bidId = bidId;
        this.userId = userId;
        this.auctionId = auctionId;
        this.amount = amount;
    }

    public Bid(User userId, Auction auctionId, double amount) {
        this.userId = userId;
        this.auctionId = auctionId;
        this.amount = amount;
    }

    public Bid() {

    }

    public int getBidId() {
        return bidId;
    }

    public void setBidId(int bidId) {
        this.bidId = bidId;
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
        return "bid{" +
                "bidId=" + bidId +
                ", userId=" + userId.getUsername() +
                ", auctionId=" + auctionId.getProduct().getProductName() +
                ", amount=" + amount +
                '}';
    }
}

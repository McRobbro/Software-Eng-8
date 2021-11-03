package Software.Engineering.Gruppe.Model;

import java.time.Duration;
import java.time.LocalDateTime;


public class Auction {
    private int auctionId;
    private Store storeId;
    private Product productId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;


    public Auction(int auctionId, Store storeId, Product productId, LocalDateTime startTime, LocalDateTime endTime) {
        this.auctionId = auctionId;
        this.storeId = storeId;
        this.productId = productId;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public Auction(Store storeId, Product productId, LocalDateTime startTime, LocalDateTime endTime) {
        this.storeId = storeId;
        this.productId = productId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getAuctionTimeDurationInMin() {
        Duration duration = Duration.between(startTime, endTime);
        return duration.toMinutes();
    }


    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public Store getStoreId() {
        return storeId;
    }

    public void setStoreId(Store storeId) {
        this.storeId = storeId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Auction{" +
                "auctionId=" + auctionId +
                ", storeId=" + storeId +
                ", productId=" + productId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}


package Software.Engineering.Gruppe.Model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;


public class Auction {
    private int auctionId;
    private Store store;
    private Product product;
    private double startPrice;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime startTime;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime endTime;

    public Auction(int auctionId, Store store, Product product, double startPrice, LocalDateTime startTime, LocalDateTime endTime) {
        this.auctionId = auctionId;
        this.store = store;
        this.product = product;
        this.startPrice = startPrice;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Auction(Store store, Product product, double startPrice, LocalDateTime startTime, LocalDateTime endTime) {
        this.store = store;
        this.product = product;
        this.startPrice = startPrice;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getAuctionTimeDurationInMin() {
        Duration duration = Duration.between(startTime, endTime);
        return duration.toMinutes();
    }


    public double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
                "auctionId=" + auctionId + "\n" +
                ", store=" + store + "\n" +
                ", product=" + product + "\n" +
                ", startTime=" + startTime + "\n" +
                ", endTime=" + endTime + "\n" +
                '}';
    }
}


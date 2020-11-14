package laskoski.f.felipe.SmartInvest.Transactions.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    Asset asset;
    Integer shares_number;
    Double price;

    @Enumerated(EnumType.STRING)
    TransactionType type;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    LocalDate date;

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public Integer getShares_number() {
        return shares_number;
    }

    public void setShares_number(Integer shares_number) {
        this.shares_number = shares_number;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}

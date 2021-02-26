package laskoski.f.felipe.SmartInvest.Transactions.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    Asset asset;
    @NotNull(message = "Number of shares is a mandatory field")
    @Min(1)
    Integer shares_number;
    @NotNull(message = "Price is a mandatory field")
    Double price;
    @NotNull(message = "Date is a mandatory field")
    LocalDate date;
    @Enumerated(EnumType.STRING)
    @Valid
    TransactionType type;

    public Transaction(){}

    public Transaction(Asset asset, Integer shares_number, Double price, TransactionType transactionType, LocalDate date){
        this.asset = asset;
        this.shares_number = shares_number;
        this.price = price;
        this.type = transactionType;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


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

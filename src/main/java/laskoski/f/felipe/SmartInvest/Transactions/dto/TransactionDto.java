package laskoski.f.felipe.SmartInvest.Transactions.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import laskoski.f.felipe.SmartInvest.Transactions.model.Transaction;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public class TransactionDto {
    Long id;
    LocalDate date;
    String asset;
    Integer shares_number;
    Double price;
    String type;

    String username;


    public TransactionDto(Transaction transaction){
        this.id = transaction.getId();
        this.asset = transaction.getAsset().getCode();
        this.shares_number = transaction.getShares_number();
        this.price = transaction.getPrice();
        this.type = transaction.getType().name();
        this.date = transaction.getDate();
        this.username = transaction.getUsername();
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static Page<TransactionDto> converter(Page<Transaction> transactions){
        return transactions.map(TransactionDto::new);
    }
}

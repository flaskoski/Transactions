package laskoski.f.felipe.SmartInvest.Transactions.dto;

import laskoski.f.felipe.SmartInvest.Transactions.model.Asset;
import laskoski.f.felipe.SmartInvest.Transactions.model.Transaction;
import laskoski.f.felipe.SmartInvest.Transactions.model.TransactionType;
import laskoski.f.felipe.SmartInvest.Transactions.repository.AssetRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class TransactionForm {
    @NotNull @NotEmpty @Length(min = 5, max = 8)
    String asset;
    Integer shares_number;
    Double price;
    String type;
    LocalDate date;

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public Transaction convert(AssetRepository assetRepository) {
        return new Transaction(assetRepository.findByCode(this.asset), this.shares_number,
                this.price, TransactionType.valueOf(this.type), this.date);
    }

    public Asset updateFromRepository(Long id, AssetRepository assetRepository) {
        Asset assetToUpdate = assetRepository.getOne(id);
        assetToUpdate.setCode(this.asset);
        return assetToUpdate;
    }

    public Asset update(Asset assetToUpdate) {
        assetToUpdate.setCode(this.asset);
        return assetToUpdate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
}

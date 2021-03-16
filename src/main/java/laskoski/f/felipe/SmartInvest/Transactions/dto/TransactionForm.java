package laskoski.f.felipe.SmartInvest.Transactions.dto;

import laskoski.f.felipe.SmartInvest.Transactions.model.Asset;
import laskoski.f.felipe.SmartInvest.Transactions.model.Transaction;
import laskoski.f.felipe.SmartInvest.Transactions.model.TransactionType;
import laskoski.f.felipe.SmartInvest.Transactions.repository.AssetRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

public class TransactionForm {
    @NotNull @NotEmpty @Length(min = 5, max = 8)
    String asset;
    @NotEmpty
    String username;
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

    public Transaction convert(AssetRepository assetRepository) throws NoSuchElementException {
        Optional<Asset> possibleAsset = assetRepository.findByCode(this.asset);
        if(possibleAsset.isPresent())
            return new Transaction(this.username, possibleAsset.get(), this.shares_number,
                    this.price, TransactionType.valueOf(this.type), this.date);
        else throw new NoSuchElementException("Asset of the transaction was not found!");
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

package laskoski.f.felipe.SmartInvest.Transactions.dto;

import laskoski.f.felipe.SmartInvest.Transactions.model.TransactionType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

public class TransactionFiltersDto {
    List<String> asset;

    @Enumerated(EnumType.STRING)
    List<TransactionType> type;

    public TransactionFiltersDto(){}

    public List<String> getAsset() {
        return asset;
    }

    public void setAsset(List<String> asset) {
        this.asset = asset;
    }

    public List<TransactionType> getType() {
        return type;
    }

    public void setType(List<TransactionType> type) {
        this.type = type;
    }
}

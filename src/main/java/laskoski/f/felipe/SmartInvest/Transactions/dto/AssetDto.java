package laskoski.f.felipe.SmartInvest.Transactions.dto;

import laskoski.f.felipe.SmartInvest.Transactions.model.Asset;

public class AssetDto {
    String code;
    Long id;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public AssetDto(Asset asset){
        this.code = asset.getCode();
        this.id = asset.getId();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AssetDto(){}
}

package laskoski.f.felipe.SmartInvest.Transactions.dto;

import laskoski.f.felipe.SmartInvest.Transactions.model.Asset;

public class AssetDto {
    String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public AssetDto(Asset asset){
        this.code = asset.getCode();
    }
    public AssetDto(){}
}

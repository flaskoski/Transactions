package laskoski.f.felipe.SmartInvest.Transactions.dto;

import laskoski.f.felipe.SmartInvest.Transactions.model.Asset;
import laskoski.f.felipe.SmartInvest.Transactions.model.AssetType;
import laskoski.f.felipe.SmartInvest.Transactions.repository.AssetRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class AssetForm {
    @NotNull @NotEmpty @Length(min = 5, max = 8)
    String code;
    @NotEmpty
    String type;
    @NotBlank
    String username;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Asset convert() {
        return new Asset(this.username, this.code, AssetType.valueOf(this.type));
    }

    public Asset updateFromRepository(Long id, AssetRepository assetRepository) {
        Asset assetToUpdate = assetRepository.getOne(id);
        assetToUpdate.setCode(this.code);
        return assetToUpdate;
    }

    public Asset update(Asset assetToUpdate) {
        assetToUpdate.setCode(this.code);
        return assetToUpdate;
    }
}

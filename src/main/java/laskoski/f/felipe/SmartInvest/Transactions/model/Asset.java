package laskoski.f.felipe.SmartInvest.Transactions.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Entity
public class Asset {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotNull @NotEmpty @Length(min = 5, max = 8)
    String code;
    @Enumerated(EnumType.STRING)
    AssetType type;
    @NotBlank
    String username;

    public AssetType getType() {
        return type;
    }

    public void setType(AssetType type) {
        this.type = type;
    }

    public Asset(String username, String code, AssetType type) {
        this.code = code;
        this.type = type;
        this.username = username;
    }
    public Asset(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}

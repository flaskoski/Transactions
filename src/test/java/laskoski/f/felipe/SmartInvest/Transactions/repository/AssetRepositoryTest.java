package laskoski.f.felipe.SmartInvest.Transactions.repository;

import laskoski.f.felipe.SmartInvest.Transactions.model.Asset;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.text.html.Option;
import java.util.Optional;

@RunWith(SpringRunner.class)
//for repository DI
@DataJpaTest
//do not replace mySQL by the in memory database
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class AssetRepositoryTest {

    @Autowired
    private AssetRepository assetRepository;

    @Test
    public void shouldFindAssetByCode(){
        String code = "PETR4";
        String username = "flaskoski";
        Optional<Asset> asset = assetRepository.findByUsernameAndCode(username, code);

        if(asset.isPresent())
            Assert.assertEquals(asset.get().getCode(), code);
        else
            Assert.fail();
    }

}

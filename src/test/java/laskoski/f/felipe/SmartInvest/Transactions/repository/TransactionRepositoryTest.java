package laskoski.f.felipe.SmartInvest.Transactions.repository;

import laskoski.f.felipe.SmartInvest.Transactions.model.Asset;
import laskoski.f.felipe.SmartInvest.Transactions.model.Transaction;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
//for repository DI
@DataJpaTest
//do not replace mySQL by the in memory database
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void shouldFindTransactionPage(){
        String code = "PETR4";
        Pageable pageable = PageRequest.of(0, 10,  Sort.Direction.ASC, "asset");
        Page<Transaction> transactionPage = transactionRepository.findByAssetCode(code, pageable);
        Assert.assertNotNull(transactionPage);
        if (!transactionPage.isEmpty())
            Assert.assertEquals(code, transactionPage.stream().findFirst().get().getAsset().getCode());
    }

}

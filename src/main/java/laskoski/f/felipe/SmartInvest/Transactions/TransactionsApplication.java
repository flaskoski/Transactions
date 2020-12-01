package laskoski.f.felipe.SmartInvest.Transactions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
public class TransactionsApplication {
	public static void main(String[] args) {
		SpringApplication.run(TransactionsApplication.class, args);
	}

}

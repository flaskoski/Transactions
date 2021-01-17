package laskoski.f.felipe.SmartInvest.Transactions.swagger;

import laskoski.f.felipe.SmartInvest.Transactions.configuration.ProductionConfigurations;
import laskoski.f.felipe.SmartInvest.Transactions.security.DevSecurityConfigurations;
import laskoski.f.felipe.SmartInvest.Transactions.security.SecurityConfigurations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public Docket transactionsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("laskoski.f.felipe.SmartInvest"))
                .paths(PathSelectors.any())
                .build()
                .ignoredParameterTypes(DevSecurityConfigurations.class,
                        SecurityConfigurations.class, ProductionConfigurations.class);
//                .pathMapping("/")
    }
}

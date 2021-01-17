package laskoski.f.felipe.SmartInvest.Transactions.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Profile("dev")
@EnableWebSecurity
@Configuration
public class DevSecurityConfigurations extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//add .cors() --> http.cors().[...] to allow CORS to be processed
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                .antMatchers("/**").permitAll()
//        https://docs.spring.io/spring-security/site/docs/3.2.0.CI-SNAPSHOT/reference/html/csrf.html
                .and().csrf().disable();
//                .antMatchers(HttpMethod.POST, "/transactions").permitAll()
//                .antMatchers(HttpMethod.GET, "assets/*").permitAll()
//                .anyRequest().permitAll();
    }
}

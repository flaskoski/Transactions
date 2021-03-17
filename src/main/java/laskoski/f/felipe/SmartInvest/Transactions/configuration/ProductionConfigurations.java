package laskoski.f.felipe.SmartInvest.Transactions.configuration;

//import com.amazonaws.services.secretsmanager.AWSSecretsManager;
//import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
//import com.amazonaws.services.secretsmanager.model.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.aws.autoconfigure.context.ContextInstanceDataAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("prod")
//exclude error on the attempt to get EC2 instance id (for testing production locally)
//@EnableAutoConfiguration(exclude = {ContextInstanceDataAutoConfiguration.class})
public class ProductionConfigurations {

    @ConfigurationProperties(prefix="spring.datasource")
    @Bean
//    if more than one datasource is initialized, define the primary for the autowired
//    @Primary
    public HikariDataSource getDataSource() throws Exception {
            return DataSourceBuilder.create().type(HikariDataSource.class)
                    .url("jdbc:mysql://"+ getDBUrl() + "?useTimezone=true&serverTimezone=UTC")
                    .username(getDBUsername()).password(getDBSecret()).build();
    }



    @Value("${tf-RDS_SMARTINVEST_URL}")
    private String databaseUrl;
    String getDBUrl() {
        return databaseUrl;
    }

    @Value("${tf-RDS_SMARTINVEST_PASSWORD}")
    private String databaseSecret;
    String getDBSecret() {
        return databaseSecret;
    }

    @Value("${tf-RDS_SMARTINVEST_username}")
    private String databaseUsername;
    String getDBUsername() {
        return databaseUsername;
    }

//using Secrets manager
//    String getDBSecret_fromSecretManager() throws Exception {
//        String secretName = "prod/smartinvest/mysql-db";
//        String region = "sa-east-1";
//
//        // Create a Secrets Manager client
//        AWSSecretsManager client  = AWSSecretsManagerClientBuilder.standard()
//                .withRegion(region)
//                .build();
//
//        // In this sample we only handle the specific exceptions for the 'GetSecretValue' API.
//        // See https://docs.aws.amazon.com/secretsmanager/latest/apireference/API_GetSecretValue.html
//        // We rethrow the exception by default.
//
//        String decodedBinarySecret;
//        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
//                .withSecretId(secretName);
//        GetSecretValueResult getSecretValueResult;
//
////        try {
//            getSecretValueResult = client.getSecretValue(getSecretValueRequest);
////        } catch (DecryptionFailureException e) {
////            // Secrets Manager can't decrypt the protected secret text using the provided KMS key.
////            throw e;
////        } catch (InternalServiceErrorException e) {
////            // An error occurred on the server side.
////            throw e;
////        } catch (InvalidParameterException e) {
////            // You provided  an invalid value for a parameter.
////            throw e;
////        } catch (InvalidRequestException e) {
////            // You provided a parameter value that is not valid for the current state of the resource.
////            throw e;
////        } catch (ResourceNotFoundException e) {
////            // We can't find the resource that you asked for.
////            throw e;
////        }
//
//        // Decrypts secret using the associated KMS CMK.
//        // Depending on whether the secret is a string or binary, one of these fields will be populated.
//        if (getSecretValueResult.getSecretString() != null) {
//            String secret = getSecretValueResult.getSecretString();
//            try {
//                return new ObjectMapper().readValue(secret, DatabaseCredentials.class).password;
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//        }
//        else {
//            throw new Exception("Failed to obtain DB credentials!");
//            //decodedBinarySecret = new String(Base64.getDecoder().decode(getSecretValueResult.getSecretBinary()).array());
//        }
//
//        // Your code goes here.
//        return null;
//    }

    //Ignore additional properties

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class DatabaseCredentials{
        String username;
        String password;

        DatabaseCredentials(){}

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}


package com.example.microservicescurrencyexchange;

import com.example.microservicescurrencyexchange.properties.RabbitEmailSenderProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RabbitEmailSenderProperties.class)
public class MicroservicesCurrencyExchangeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicesCurrencyExchangeApplication.class, args);
    }

}

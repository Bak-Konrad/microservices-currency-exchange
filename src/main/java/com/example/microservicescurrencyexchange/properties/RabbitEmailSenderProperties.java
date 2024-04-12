package com.example.microservicescurrencyexchange.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "rabbit")
public class RabbitEmailSenderProperties {

    private String emailQueue;
}

package com.example.microservicescurrencyexchange.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class ConvertResultDataSender {
    private final RabbitTemplate rabbitTemplate;
    @Value("${rabbit.email-queue}")
    private String rateQueue;

    public void sendEmail(ConvertResultData messageData) {
        rabbitTemplate.convertAndSend(rateQueue, messageData);
        log.info("message sent: {}", messageData);
    }
}

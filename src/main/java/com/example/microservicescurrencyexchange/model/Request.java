package com.example.microservicescurrencyexchange.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Request {
    private String from;
    private String to;
    private BigDecimal amount;
}
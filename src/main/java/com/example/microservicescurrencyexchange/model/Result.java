package com.example.microservicescurrencyexchange.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Result {

    private String currencyFrom;
    private String currencyTo;
    private BigDecimal amount;
    private BigDecimal result;
    private BigDecimal rate;

}

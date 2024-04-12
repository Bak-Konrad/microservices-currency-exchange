package com.example.microservicescurrencyexchange.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class RateDto {
    private String currency;

    private String code;
    private BigDecimal bid;

    private BigDecimal ask;
}
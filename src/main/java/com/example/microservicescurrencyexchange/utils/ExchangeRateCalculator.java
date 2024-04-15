package com.example.microservicescurrencyexchange.utils;

import com.example.microservicescurrencyexchange.model.Rate;
import com.example.microservicescurrencyexchange.model.Request;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class ExchangeRateCalculator {

    public BigDecimal calculateRate(Rate from, Rate to) {
        return from.getBid().divide(to.getAsk(), RoundingMode.HALF_UP);
    }

    public BigDecimal calculateResult(Request request, BigDecimal rate) {
        BigDecimal amount = request.getAmount();
        return amount.multiply(rate);
    }
}

package com.example.microservicescurrencyexchange.service;

import com.example.microservicescurrencyexchange.email.ConvertResultData;
import com.example.microservicescurrencyexchange.email.ConvertResultDataSender;
import com.example.microservicescurrencyexchange.model.Rate;
import com.example.microservicescurrencyexchange.model.Request;
import com.example.microservicescurrencyexchange.model.Result;
import com.example.microservicescurrencyexchange.repository.ExchangeRepository;
import com.example.microservicescurrencyexchange.utils.ExchangeRateCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ConversionService {
    private final ExchangeRepository exchangeRepository;
    private final ConvertResultDataSender convertResultDataSender;
    private final ExchangeRateCalculator exchangeRateCalculator;

    public Result convert(Request request, String emailAddress) {
        Result resultInfo = conversionResultBuilder(request);

        if (!hasRole("ROLE_ADMIN")) {
            convertResultDataSender.sendEmail(ConvertResultData.builder()
                    .emailAddress(emailAddress)
                    .result(resultInfo)
                    .build());
        }
        return resultInfo;
    }

    private Result conversionResultBuilder(Request request) {
       Rate rateFrom = exchangeRepository.findByCode(request.getFrom())
                .orElseThrow(() -> new EntityNotFoundException("Currency not found"));
        Rate rateTo = exchangeRepository.findByCode(request.getTo())
                .orElseThrow(() -> new EntityNotFoundException("Currency not found"));

        BigDecimal rate = exchangeRateCalculator.calculateRate(rateFrom, rateTo);
        BigDecimal result = exchangeRateCalculator.calculateResult(request, rate);
        return Result.builder()
                .result(result)
                .currencyFrom(rateFrom.getCode())
                .currencyTo(rateTo.getCode())
                .amount(request.getAmount())
                .rate(rate)
                .build();
    }

    private boolean hasRole(String role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication != null && authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals(role));
    }
}

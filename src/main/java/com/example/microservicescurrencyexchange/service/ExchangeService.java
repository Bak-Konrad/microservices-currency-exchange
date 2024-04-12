package com.example.microservicescurrencyexchange.service;

import com.example.microservicescurrencyexchange.mapper.RateMapperMapstruct;
import com.example.microservicescurrencyexchange.model.Rate;
import com.example.microservicescurrencyexchange.model.dto.RateDto;
import com.example.microservicescurrencyexchange.repository.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExchangeService {// TODO: 05.12.2023 wywalić do jednego serwisu, bardziej ten drugi wywalic do tego :
    private final ExchangeRepository exchangeRepository;
    private final RateMapperMapstruct rateMapper;

    public List<RateDto> findAll() {
        List<Rate> rates = exchangeRepository.findAll();
        return rates.stream()
                .map(rateMapper::mapToDto)
                .toList();
    }

    public RateDto findByCurrency(String currency) {
        Rate rate = exchangeRepository.findByCode(currency).orElseThrow();
        return rateMapper.mapToDto(rate);
    }



}

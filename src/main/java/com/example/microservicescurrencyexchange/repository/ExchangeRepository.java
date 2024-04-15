package com.example.microservicescurrencyexchange.repository;

import com.example.microservicescurrencyexchange.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExchangeRepository extends JpaRepository<Rate, Integer> {
    Optional<Rate> findByCode(String currencyCode);
}

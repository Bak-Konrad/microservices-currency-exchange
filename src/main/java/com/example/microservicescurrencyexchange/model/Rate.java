package com.example.microservicescurrencyexchange.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Rate {

    private String currency;
    @Id
    private String code;
    private BigDecimal bid;
    private BigDecimal ask;

}
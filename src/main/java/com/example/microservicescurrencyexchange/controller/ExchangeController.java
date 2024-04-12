package com.example.microservicescurrencyexchange.controller;

import com.example.microservicescurrencyexchange.model.Request;
import com.example.microservicescurrencyexchange.model.Result;
import com.example.microservicescurrencyexchange.model.dto.RateDto;
import com.example.microservicescurrencyexchange.service.ConversionService;
import com.example.microservicescurrencyexchange.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/exchange")
public class ExchangeController {
//    3 exchange-api
//- aplikacja ma wystawiać endpointy, które pozwalają na uzyskanie
//    informacji takich jak
//	- wszystkie dostępne waluty (brak autentykacji)
//	- informacje o kursach wskazanej waluty (brak autentykacji)
//	- wymiana walut (wskazanie "z", "do", "kwota") (admin)
//            - wymiana walut z potwierdzeniem (każdy użytkownik)
//- aplikacja ma używać security i weryfikować użytkowników zgodnie
//    z powyższą rozspiską usług.
//            - wysyłka na maila ma się odbywać na adres przypisany do użytkownika
//    wysyłającego zapytanie i ma być wywoływana eventem.

    private final ExchangeService exchangeService;
    private final ConversionService conversionService;

    @GetMapping("/symbols")// TODO: 05.12.2023 to ma zwracać tylko pary nazwa - kod, a nie wszystko
    public ResponseEntity<List<RateDto>> findAllRate() {
        return new ResponseEntity<>( exchangeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{currency}")
    public ResponseEntity<RateDto> findByCurrency(@PathVariable String currency) {
        return new ResponseEntity<>(exchangeService.findByCurrency(currency), HttpStatus.OK);
    }

    @GetMapping("/convert")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<Result> convert(@RequestBody Request request, @RequestParam String emailAddress) {
        return new ResponseEntity<>(conversionService.convert(request,emailAddress),HttpStatus.OK);
    }
}

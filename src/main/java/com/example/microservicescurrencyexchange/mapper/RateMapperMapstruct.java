package com.example.microservicescurrencyexchange.mapper;

import com.example.microservicescurrencyexchange.model.Rate;
import com.example.microservicescurrencyexchange.model.dto.RateDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RateMapperMapstruct {
    RateDto mapToDto(Rate data);
}

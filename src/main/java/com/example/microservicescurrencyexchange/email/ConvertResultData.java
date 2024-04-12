package com.example.microservicescurrencyexchange.email;

import com.example.microservicescurrencyexchange.model.Result;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConvertResultData {
    private Result result;
    private String emailAddress;
}

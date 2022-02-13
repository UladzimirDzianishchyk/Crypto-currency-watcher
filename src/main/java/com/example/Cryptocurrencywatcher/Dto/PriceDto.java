package com.example.Cryptocurrencywatcher.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceDto {

    Long id;

    String symbol;

    @JsonProperty("price_usd")
    Double price;

    LocalDateTime date;

}

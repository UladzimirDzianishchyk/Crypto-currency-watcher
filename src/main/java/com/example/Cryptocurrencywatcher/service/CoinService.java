package com.example.Cryptocurrencywatcher.service;

import com.example.Cryptocurrencywatcher.Dto.CoinDto;
import com.example.Cryptocurrencywatcher.Dto.PriceDto;
import com.example.Cryptocurrencywatcher.entity.UserPrice;

import java.util.List;

public interface CoinService {
    List<CoinDto> getAll();
    PriceDto getLastPrice(String symbol);
    void addPriceToTracking(UserPrice userPrice);
}

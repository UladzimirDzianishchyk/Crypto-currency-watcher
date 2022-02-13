package com.example.Cryptocurrencywatcher.serviceImpl;

import com.example.Cryptocurrencywatcher.Dto.CoinDto;
import com.example.Cryptocurrencywatcher.Dto.PriceDto;
import com.example.Cryptocurrencywatcher.entity.*;
import com.example.Cryptocurrencywatcher.exception.EntityNotFoundException;
import com.example.Cryptocurrencywatcher.repository.BTCRepository;
import com.example.Cryptocurrencywatcher.repository.CoinRepository;
import com.example.Cryptocurrencywatcher.repository.ETHRepository;
import com.example.Cryptocurrencywatcher.repository.SOLRepository;
import com.example.Cryptocurrencywatcher.service.CoinService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class CoinServiceImpl implements CoinService {

    private final CoinRepository coinRepository;
    private final BTCRepository btcRepository;
    private final ETHRepository ethRepository;
    private final SOLRepository solRepository;

    public CoinServiceImpl(CoinRepository coinRepository, BTCRepository btcRepository, ETHRepository ethRepository, SOLRepository solRepository) {
        this.coinRepository = coinRepository;
        this.btcRepository = btcRepository;
        this.ethRepository = ethRepository;
        this.solRepository = solRepository;
    }

    List<UserPrice> prices = new ArrayList<>();

    @Override
    public List<CoinDto> getAll() {
        List<Coin> coins = coinRepository.findAll();
        List<CoinDto> dtoList = new ArrayList<>();
        for (Coin c : coins) {
            dtoList.add(c.toDto());
        }
        return dtoList;
    }

    @Override
    public PriceDto getLastPrice(String symbol) {
        return switch (symbol) {
            case "ETH" -> ethRepository.findFirstBySymbolOrderByDateOfReceivingDesc(symbol)
                    .orElseThrow(() ->new EntityNotFoundException("Coin " + symbol))
                    .toDto();
            case "BTC" -> btcRepository.findFirstBySymbolOrderByDateOfReceivingDesc(symbol)
                    .orElseThrow(() ->new EntityNotFoundException("Coin " + symbol))
                    .toDto();
            case "SOL" -> solRepository.findFirstBySymbolOrderByDateOfReceivingDesc(symbol)
                    .orElseThrow(() ->new EntityNotFoundException("Coin " + symbol))
                    .toDto();
            default -> throw new EntityNotFoundException("Coin " + symbol);
        };
    }

    @Scheduled(fixedRate = 60 * 1000)
    private void startGetPrice(){
        WebClient webClient = WebClient.create("https://api.coinlore.net/api/ticker");
        ObjectMapper mapper = new ObjectMapper();
        List<PriceDto> btc = Arrays.stream(webClient.get().uri("/?id=90")
                        .accept(MediaType.APPLICATION_JSON).retrieve()
                        .bodyToMono(Object[].class).block())
                .map(object -> mapper.convertValue(object, PriceDto.class))
                .collect(Collectors.toList());

        List<PriceDto> eth = Arrays.stream(webClient.get().uri("/?id=80")
                        .accept(MediaType.APPLICATION_JSON).retrieve()
                        .bodyToMono(Object[].class).block())
                .map(object -> mapper.convertValue(object, PriceDto.class))
                .collect(Collectors.toList());
        List<PriceDto> sol = Arrays.stream(webClient.get().uri("/?id=48543")
                        .accept(MediaType.APPLICATION_JSON).retrieve()
                        .bodyToMono(Object[].class).block())
                .map(object -> mapper.convertValue(object, PriceDto.class))
                .collect(Collectors.toList());

        BTCPrice btcPrice = BTCPrice.fromDto(btc.get(0));
        ETHPrice ethPrice = ETHPrice.fromDto(eth.get(0));
        SOLPrice solPrice = SOLPrice.fromDto(sol.get(0));


        btcRepository.save(btcPrice);
        ethRepository.save(ethPrice);
        solRepository.save(solPrice);


    }


    @Override
    public void addPriceToTracking(UserPrice userPrice){
        prices.add(userPrice);
    }


    @Scheduled(fixedRate = 60 * 1000)
    private void trackPrice(){

        if (!prices.isEmpty()) {
            for (UserPrice price : prices) {
                System.out.println(prices);
                double dbPrice = getLastPrice(price.getSymbol()).getPrice();
                double percent = (price.getUserPrice() - dbPrice) / dbPrice * 100;
                if (1 < Math.abs(percent)) {
                    Logger.getGlobal().warning("Price " + price.getSymbol() + " changed " + percent);
                }
            }
        }

    }
}

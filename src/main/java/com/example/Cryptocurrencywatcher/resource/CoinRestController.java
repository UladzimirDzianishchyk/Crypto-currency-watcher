package com.example.Cryptocurrencywatcher.resource;


import com.example.Cryptocurrencywatcher.Dto.CoinDto;
import com.example.Cryptocurrencywatcher.Dto.PriceDto;
import com.example.Cryptocurrencywatcher.service.CoinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/coins")
public class CoinRestController {


    private final CoinService coinService;

    public CoinRestController(CoinService coinService) {
        this.coinService = coinService;
    }


    @GetMapping
    public ResponseEntity<List<CoinDto>> getAll(){
        return new ResponseEntity<>(coinService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{symbol}")
    public ResponseEntity<PriceDto> getPrice(@PathVariable String symbol){
        return new ResponseEntity<>(coinService.getLastPrice(symbol),HttpStatus.OK);
    }
}

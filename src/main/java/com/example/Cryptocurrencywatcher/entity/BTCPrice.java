package com.example.Cryptocurrencywatcher.entity;

import com.example.Cryptocurrencywatcher.Dto.PriceDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "btc_price")
public class BTCPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "external_id")
    Long externalId;

    @Column(name = "symbol")
    String symbol;

    @Column(name = "resave_date", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.ms")
    LocalDateTime dateOfReceiving = LocalDateTime.now();

    @Column(name = "coin_price")
    Double coinPrice;

    public PriceDto toDto(){
        PriceDto dto = new PriceDto();
        dto.setId(this.externalId);
        dto.setSymbol("BTC");
        dto.setPrice(this.coinPrice);
        dto.setDate(this.dateOfReceiving);
        return dto;
    }

    public static BTCPrice fromDto(PriceDto dto){
        BTCPrice price = new BTCPrice();
        price.setExternalId(dto.getId());
        price.setSymbol(dto.getSymbol());
        price.setCoinPrice(dto.getPrice());
        return price;
    }
}

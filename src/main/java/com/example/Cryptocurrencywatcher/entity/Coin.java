package com.example.Cryptocurrencywatcher.entity;


import com.example.Cryptocurrencywatcher.Dto.CoinDto;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "coins")
public class Coin {

    @Id
    Long id;

    @Column(name = "external_id")
    Long externalId;

    @Column(name = "symbol")
    String symbol;

    public CoinDto toDto(){
        CoinDto dto = new CoinDto();
        dto.setId(this.externalId);
        dto.setSymbol(this.symbol);
        return dto;
    }
}

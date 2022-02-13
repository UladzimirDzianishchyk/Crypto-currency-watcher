package com.example.Cryptocurrencywatcher.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_coin_price")
public class UserPrice {

    public UserPrice(String userName, String symbol, Double userPrice) {
        this.userName = userName;
        this.symbol = symbol;
        this.userPrice = userPrice;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "user_anme")
    String userName;

    @Column(name = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.ms")
    LocalDateTime memberDate = LocalDateTime.now();

    @Column(name = "symbol")
    String symbol;

    @Column(name = "member_price")
    Double userPrice;
}

package com.example.Cryptocurrencywatcher.repository;

import com.example.Cryptocurrencywatcher.entity.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinRepository extends JpaRepository<Coin,Long> {
}

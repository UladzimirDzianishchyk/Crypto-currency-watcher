package com.example.Cryptocurrencywatcher.repository;

import com.example.Cryptocurrencywatcher.entity.BTCPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BTCRepository extends JpaRepository<BTCPrice,Long> {

    Optional<BTCPrice> findFirstBySymbolOrderByDateOfReceivingDesc(String symbol);
}

package com.example.Cryptocurrencywatcher.repository;

import com.example.Cryptocurrencywatcher.entity.SOLPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SOLRepository extends JpaRepository<SOLPrice,Long> {

    Optional<SOLPrice> findFirstBySymbolOrderByDateOfReceivingDesc(String symbol);
}

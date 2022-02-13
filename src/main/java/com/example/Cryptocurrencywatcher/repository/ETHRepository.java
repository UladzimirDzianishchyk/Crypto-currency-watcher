package com.example.Cryptocurrencywatcher.repository;

import com.example.Cryptocurrencywatcher.entity.ETHPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ETHRepository extends JpaRepository<ETHPrice,Long> {

    Optional<ETHPrice> findFirstBySymbolOrderByDateOfReceivingDesc(String symbol);
}

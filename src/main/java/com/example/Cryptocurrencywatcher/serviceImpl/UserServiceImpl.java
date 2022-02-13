package com.example.Cryptocurrencywatcher.serviceImpl;

import com.example.Cryptocurrencywatcher.entity.UserPrice;
import com.example.Cryptocurrencywatcher.repository.UserPriceRepository;
import com.example.Cryptocurrencywatcher.service.CoinService;
import com.example.Cryptocurrencywatcher.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserPriceRepository userPriceRepository;
    private final CoinService coinService;

    public UserServiceImpl(UserPriceRepository userPriceRepository, CoinService coinService) {
        this.userPriceRepository = userPriceRepository;
        this.coinService = coinService;
    }

    @Override
    public void save(UserPrice userPrice) {
        userPriceRepository.save(userPrice);
        coinService.addPriceToTracking(userPrice);
    }



}

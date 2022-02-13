package com.example.Cryptocurrencywatcher.resource;

import com.example.Cryptocurrencywatcher.entity.UserPrice;
import com.example.Cryptocurrencywatcher.service.CoinService;
import com.example.Cryptocurrencywatcher.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
public class UserRestController {

    private final UserService userService;
    private final CoinService coinService;

    public UserRestController(UserService userService, CoinService coinService) {
        this.userService = userService;
        this.coinService = coinService;
    }

    @PostMapping
    public ResponseEntity<String> notify(@RequestParam String userName, String symbol){
        UserPrice userPrice = new UserPrice(userName, symbol, coinService.getLastPrice(symbol).getPrice());
        userService.save(userPrice);
        return new ResponseEntity<>("Price fixed", HttpStatus.OK);
    }
}

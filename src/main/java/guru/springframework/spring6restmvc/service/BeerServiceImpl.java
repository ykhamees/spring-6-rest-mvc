package guru.springframework.spring6restmvc.service;

import guru.springframework.spring6restmvc.model.Beer;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public Beer getBeerById(UUID beerId) {
        return Beer.builder().id(beerId).version(1).beerName("Galaxy Cat").beerStyle("Pale Ale").upc("0631234200036")
                .quantityOnHand(200).price(new BigDecimal("12.95")).createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now()).build();
    }
}

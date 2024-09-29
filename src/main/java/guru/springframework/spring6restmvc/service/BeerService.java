package guru.springframework.spring6restmvc.service;

import guru.springframework.spring6restmvc.model.Beer;

import java.util.List;
import java.util.UUID;

public interface BeerService {
    List<Beer> getBeers();

    Beer getBeerById(UUID beerId);

    Beer saveNewBeer(Beer beer);

    void updateBeer(UUID beerId, Beer beer);

    void deleteById(UUID beerId);

    void patchBeer(UUID beerId, Beer beer);
}

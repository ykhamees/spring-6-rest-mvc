package guru.springframework.spring6restmvc.service;

import guru.springframework.spring6restmvc.model.BeerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerService {
    List<BeerDTO> getBeers();

    Optional<BeerDTO> getBeerById(UUID beerId);

    BeerDTO saveNewBeer(BeerDTO beer);

    void updateBeer(UUID beerId, BeerDTO beer);

    void deleteById(UUID beerId);

    void patchBeer(UUID beerId, BeerDTO beer);
}

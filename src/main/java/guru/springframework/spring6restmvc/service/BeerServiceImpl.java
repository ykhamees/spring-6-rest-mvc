package guru.springframework.spring6restmvc.service;

import guru.springframework.spring6restmvc.model.BeerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    BeerDTO beer1 = BeerDTO.builder().id(UUID.randomUUID()).version(1).beerName("Galaxy Cat").beerStyle("Pale Ale")
            .upc("0631234200036")
            .quantityOnHand(200).price(new BigDecimal("13.95")).createdDate(LocalDateTime.now())
            .updateDate(LocalDateTime.now()).build();

    BeerDTO beer2 = BeerDTO.builder().id(UUID.randomUUID()).version(1).beerName("Galaxy Dog").beerStyle("Dark Ale")
            .upc("0631214200036")
            .quantityOnHand(200).price(new BigDecimal("55.95")).createdDate(LocalDateTime.now())
            .updateDate(LocalDateTime.now()).build();

    BeerDTO beer3 = BeerDTO.builder().id(UUID.randomUUID()).version(1).beerName("Galaxy Cat").beerStyle("Pale Ale")
            .upc("0851234200026")
            .quantityOnHand(200).price(new BigDecimal("12.95")).createdDate(LocalDateTime.now())
            .updateDate(LocalDateTime.now()).build();

    Map<UUID, BeerDTO> beerMap;

    public BeerServiceImpl() {
        beerMap = Map.of(beer1.getId(), beer1, beer2.getId(), beer2, beer3.getId(), beer3);
    }

    @Override
    public List<BeerDTO> getBeers() {
        return new ArrayList<>(beerMap.values());
    }

    @Override
    public Optional<BeerDTO> getBeerById(UUID beerId) {
        log.debug("In BeerController.getBeerById");

        return Optional.of(beerMap.get(beerId));
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beer) {
        BeerDTO savedBeer = BeerDTO.builder().id(UUID.randomUUID()).version(1).beerName(beer.getBeerName())
                .beerStyle(beer.getBeerStyle()).upc(beer.getUpc()).quantityOnHand(beer.getQuantityOnHand())
                .price(beer.getPrice()).createdDate(LocalDateTime.now()).updateDate(LocalDateTime.now()).build();

        beerMap.put(savedBeer.getId(), savedBeer);

        return beerMap.get(savedBeer.getId());
    }

    @Override
    public void updateBeer(UUID beerId, BeerDTO beer) {
        BeerDTO updatedBeer = beerMap.get(beerId);

        updatedBeer.setVersion(updatedBeer.getVersion() + 1);
        updatedBeer.setBeerName(beer.getBeerName());
        updatedBeer.setBeerStyle(beer.getBeerStyle());
        updatedBeer.setUpc(beer.getUpc());
        updatedBeer.setQuantityOnHand(beer.getQuantityOnHand());
        updatedBeer.setPrice(beer.getPrice());
        updatedBeer.setUpdateDate(LocalDateTime.now());

        beerMap.put(updatedBeer.getId(), updatedBeer);
    }

    @Override
    public void deleteById(UUID beerId) {
        beerMap.remove(beerId);
    }

    @Override
    public void patchBeer(UUID beerId, BeerDTO beer) {
        BeerDTO patchedBeer = beerMap.get(beerId);

        if (beer.getBeerName() != null) {
            patchedBeer.setBeerName(beer.getBeerName());
        }

        if (beer.getBeerStyle() != null) {
            patchedBeer.setBeerStyle(beer.getBeerStyle());
        }

        if (beer.getUpc() != null) {
            patchedBeer.setUpc(beer.getUpc());
        }

        if (beer.getQuantityOnHand() != null) {
            patchedBeer.setQuantityOnHand(beer.getQuantityOnHand());
        }

        if (beer.getPrice() != null) {
            patchedBeer.setPrice(beer.getPrice());
        }

        patchedBeer.setUpdateDate(LocalDateTime.now());

        beerMap.put(patchedBeer.getId(), patchedBeer);
    }
}

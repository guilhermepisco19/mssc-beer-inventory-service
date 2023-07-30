package guru.sfg.beer.inventory.service.listeners;

import guru.sfg.beer.inventory.service.config.JmsConfig;
import guru.sfg.beer.inventory.service.domain.BeerInventory;
import guru.sfg.beer.inventory.service.repositories.BeerInventoryRepository;
import guru.sfg.common.events.NewInventoryEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NewInventoryListener {

    private final BeerInventoryRepository beerInventoryRepository;

    @JmsListener(destination = JmsConfig.NEW_INVENTORY_QUEUE)
    public void newInventoryListener(NewInventoryEvent event){
        log.debug("Got inventory: {}", event);

        BeerInventory beerInventory = beerInventoryRepository.findByBeerId(event.getBeerDto().getId());

        beerInventory.setBeerId(event.getBeerDto().getId());
        beerInventory.setUpc(event.getBeerDto().getUpc());
        beerInventory.setQuantityOnHand(event.getBeerDto().getQuantityOnHand());

        beerInventoryRepository.save(beerInventory);
    }

}

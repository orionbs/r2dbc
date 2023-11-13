package io.orionbs.resilient.database.reactive.adapter;

import io.orionbs.resilient.application.port.SelectBasketPort;
import io.orionbs.resilient.database.reactive.model.BasketModelReactiveDatabase;
import io.orionbs.resilient.database.reactive.model.FruitModelReactiveDatabase;
import io.orionbs.resilient.database.reactive.repository.BasketRepositoryReactiveDatabase;
import io.orionbs.resilient.database.reactive.repository.FruitRepositoryReactiveDatabase;
import io.orionbs.resilient.domain.model.Basket;
import io.orionbs.resilient.domain.model.Fruit;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Optional;

@Component
public class BasketAdapterReactiveDatabase implements SelectBasketPort {

    private final BasketRepositoryReactiveDatabase basketRepositoryReactiveDatabase;
    private final FruitRepositoryReactiveDatabase fruitRepositoryReactiveDatabase;

    public BasketAdapterReactiveDatabase(BasketRepositoryReactiveDatabase basketRepositoryReactiveDatabase, FruitRepositoryReactiveDatabase fruitRepositoryReactiveDatabase) {
        this.basketRepositoryReactiveDatabase = basketRepositoryReactiveDatabase;
        this.fruitRepositoryReactiveDatabase = fruitRepositoryReactiveDatabase;
    }

    @Override
    public Flux<Basket> selectBaskets() {
        return Flux
                .from(basketRepositoryReactiveDatabase.findAll())
                .subscribeOn(Schedulers.parallel())
                .flatMap(basketModelReactiveDatabase -> fruitRepositoryReactiveDatabase
                        .findAllByBasketId(basketModelReactiveDatabase.getId())
                        .doOnNext(basketModelReactiveDatabase.getFruits()::add)
                        .then(Mono.just(basketModelReactiveDatabase))
                )
                .map(basketModelReactiveDatabase -> {
                    Basket basket = new Basket();
                    Optional.of(basketModelReactiveDatabase)
                            .map(BasketModelReactiveDatabase::getId)
                            .map(String::valueOf)
                            .ifPresent(basket::setIdentifier);
                    Optional.of(basketModelReactiveDatabase)
                            .map(BasketModelReactiveDatabase::getProductAt)
                            .ifPresent(basket::setProductAt);
                    basketModelReactiveDatabase
                            .getFruits()
                            .forEach(fruitModelReactiveDatabase -> {
                                Fruit fruit = new Fruit();
                                Optional.of(fruitModelReactiveDatabase)
                                        .map(FruitModelReactiveDatabase::getId)
                                        .map(String::valueOf)
                                        .ifPresent(fruit::setIdentifier);
                                Optional.of(fruitModelReactiveDatabase)
                                        .map(FruitModelReactiveDatabase::getHarvestAt)
                                        .ifPresent(fruit::setHarvestAt);
                                basket.getFruits().add(fruit);
                            });
                    return basket;
                });
    }

}

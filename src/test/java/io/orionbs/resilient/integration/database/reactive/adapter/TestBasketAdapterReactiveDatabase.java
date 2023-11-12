package io.orionbs.resilient.integration.database.reactive.adapter;

import io.orionbs.resilient.database.reactive.adapter.BasketAdapterReactiveDatabase;
import io.orionbs.resilient.database.reactive.model.BasketModelReactiveDatabase;
import io.orionbs.resilient.database.reactive.model.FruitModelReactiveDatabase;
import io.orionbs.resilient.database.reactive.repository.BasketRepositoryReactiveDatabase;
import io.orionbs.resilient.database.reactive.repository.FruitRepositoryReactiveDatabase;
import io.orionbs.resilient.domain.model.Basket;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "test")
public class TestBasketAdapterReactiveDatabase {

    private static final String LOG = "Test Basket Adapter Reactive Database -> ";

    @Autowired
    private BasketRepositoryReactiveDatabase basketRepositoryReactiveDatabase;

    @Autowired
    private FruitRepositoryReactiveDatabase fruitRepositoryReactiveDatabase;

    @Autowired
    private BasketAdapterReactiveDatabase basketAdapterReactiveDatabase;

    @BeforeEach
    void setUp() {
        System.out.println(LOG + "Démarrage de la mise en place.");
        Flux<BasketModelReactiveDatabase> addHundredBasket = Flux
                .range(0, 100)
                .map(number -> {
                    BasketModelReactiveDatabase basketModelReactiveDatabase = new BasketModelReactiveDatabase();
                    basketModelReactiveDatabase.setProductAt(LocalDateTime.now().minusDays(number));
                    return basketModelReactiveDatabase;
                })
                .flatMap(basketModelReactiveDatabase -> basketRepositoryReactiveDatabase.save(basketModelReactiveDatabase))
                .flatMap(basketModelReactiveDatabase -> Flux
                        .range(0, 10)
                        .map(number -> {
                            FruitModelReactiveDatabase fruitModelReactiveDatabase = new FruitModelReactiveDatabase();
                            fruitModelReactiveDatabase.setHarvestAt(basketModelReactiveDatabase.getProductAt().plusDays(number));
                            fruitModelReactiveDatabase.setBasketId(basketModelReactiveDatabase.getId());
                            return fruitModelReactiveDatabase;
                        })
                        .flatMap(fruitModelReactiveDatabase -> fruitRepositoryReactiveDatabase.save(fruitModelReactiveDatabase))
                        .then(Mono.just(basketModelReactiveDatabase))
                );

        addHundredBasket
                .doOnComplete(() -> System.out.println(LOG + "Fin de la mise en place."))
                .subscribe();
    }

    @Test
    void testSelectBaskets() {
        Flux<Basket> flux = basketAdapterReactiveDatabase
                .selectBaskets()
                .log();
        StepVerifier.create(flux)
                .expectNextCount(100)
                .verifyComplete();
    }

    @AfterEach
    void cleanUp() {
        System.out.println(LOG + "Démarrage du nettoyage.");
        Mono<Void> mono = Mono.just(1)
                .then(Mono.defer(() -> fruitRepositoryReactiveDatabase.deleteAll()))
                .then(Mono.defer(() -> basketRepositoryReactiveDatabase.deleteAll()))
                .doOnTerminate(() -> System.out.println(LOG + "Fin du nettoyage."));
        StepVerifier.create(mono)
                .verifyComplete();
    }


}

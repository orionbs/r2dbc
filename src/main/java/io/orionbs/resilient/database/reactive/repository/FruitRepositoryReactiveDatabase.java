package io.orionbs.resilient.database.reactive.repository;

import io.orionbs.resilient.database.reactive.model.FruitModelReactiveDatabase;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface FruitRepositoryReactiveDatabase extends R2dbcRepository<FruitModelReactiveDatabase, Long> {
    Flux<FruitModelReactiveDatabase> findAllByBasketId(Long basketId);
}

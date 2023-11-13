package io.orionbs.resilient.database.reactive.repository;

import io.orionbs.resilient.database.reactive.model.BasketModelReactiveDatabase;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface BasketRepositoryReactiveDatabase extends R2dbcRepository<BasketModelReactiveDatabase, Long> {
}

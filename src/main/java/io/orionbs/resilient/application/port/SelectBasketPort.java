package io.orionbs.resilient.application.port;

import io.orionbs.resilient.domain.model.Basket;
import reactor.core.publisher.Flux;

public interface SelectBasketPort {
    Flux<Basket> selectBaskets();
}

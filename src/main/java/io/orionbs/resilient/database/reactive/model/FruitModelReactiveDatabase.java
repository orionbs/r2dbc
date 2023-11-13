package io.orionbs.resilient.database.reactive.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Table(schema = "PUBLIC", name = "FRUIT")
public class FruitModelReactiveDatabase {

    @Id
    @Column(value = "ID")
    private Long id;
    @Column(value = "HARVEST_AT")
    private LocalDateTime harvestAt;
    @Column(value = "BASKET_ID")
    private Long basketId;
    @Transient
    private BasketModelReactiveDatabase basket;

    public FruitModelReactiveDatabase() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getHarvestAt() {
        return harvestAt;
    }

    public void setHarvestAt(LocalDateTime harvestAt) {
        this.harvestAt = harvestAt;
    }

    public Long getBasketId() {
        return basketId;
    }

    public void setBasketId(Long basketId) {
        this.basketId = basketId;
    }

    public BasketModelReactiveDatabase getBasket() {
        return basket;
    }

    public void setBasket(BasketModelReactiveDatabase basket) {
        this.basket = basket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FruitModelReactiveDatabase that = (FruitModelReactiveDatabase) o;
        return Objects.equals(id, that.id) && Objects.equals(harvestAt, that.harvestAt) && Objects.equals(basketId, that.basketId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, harvestAt, basketId);
    }
}

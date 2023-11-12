package io.orionbs.resilient.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Fruit {
    private String identifier;
    private LocalDateTime harvestAt;
    private Basket basket;

    public Fruit() {
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public LocalDateTime getHarvestAt() {
        return harvestAt;
    }

    public void setHarvestAt(LocalDateTime harvestAt) {
        this.harvestAt = harvestAt;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fruit fruit = (Fruit) o;
        return Objects.equals(identifier, fruit.identifier) && Objects.equals(harvestAt, fruit.harvestAt) && Objects.equals(basket, fruit.basket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, harvestAt, basket);
    }

    @Override
    public String toString() {
        return "Fruit{" + "identifier='" + identifier + '\'' +
                ", harvestAt=" + harvestAt +
                ", basket=" + basket +
                '}';
    }
}

package io.orionbs.resilient.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Basket {
    private String identifier;
    private LocalDateTime productAt;
    private List<Fruit> fruits = new ArrayList<>();

    public Basket() {
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public LocalDateTime getProductAt() {
        return productAt;
    }

    public void setProductAt(LocalDateTime productAt) {
        this.productAt = productAt;
    }

    public List<Fruit> getFruits() {
        return fruits;
    }

    public void setFruits(List<Fruit> fruits) {
        this.fruits = fruits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basket basket = (Basket) o;
        return Objects.equals(identifier, basket.identifier) && Objects.equals(productAt, basket.productAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, productAt);
    }

    @Override
    public String toString() {
        return "Basket{" + "identifier='" + identifier + '\'' +
                ", productAt=" + productAt +
                ", fruits=" + fruits +
                '}';
    }
}

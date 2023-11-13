package io.orionbs.resilient.database.reactive.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table(schema = "PUBLIC", name = "BASKET")
public class BasketModelReactiveDatabase {

    @Id
    @Column(value = "ID")
    private Long id;
    @Column(value = "PRODUCT_AT")
    private LocalDateTime productAt;

    @Transient
    private List<FruitModelReactiveDatabase> fruits = new ArrayList<>();

    public BasketModelReactiveDatabase() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getProductAt() {
        return productAt;
    }

    public void setProductAt(LocalDateTime productAt) {
        this.productAt = productAt;
    }

    public List<FruitModelReactiveDatabase> getFruits() {
        return fruits;
    }

    public void setFruits(List<FruitModelReactiveDatabase> fruits) {
        this.fruits = fruits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketModelReactiveDatabase that = (BasketModelReactiveDatabase) o;
        return Objects.equals(id, that.id) && Objects.equals(productAt, that.productAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productAt);
    }

    @Override
    public String toString() {
        return "BasketModelReactiveDatabase{" + "id=" + id +
                ", productAt=" + productAt +
                '}';
    }
}

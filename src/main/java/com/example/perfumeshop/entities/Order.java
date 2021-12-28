package com.example.perfumeshop.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "per_order")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@ToString
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    long id;

    @Column(name = "number")
    long numberOfOrder;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JsonIgnore
    @ToString.Exclude
    Basket basket;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    Buyer buyer;

    public Order(long numberOfOrders) {
        this.numberOfOrder = numberOfOrders;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order number = (Order) o;
        return id == number.id && numberOfOrder == number.numberOfOrder
                && basket.equals(number.basket) && buyer.equals(number.buyer);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, numberOfOrder, basket, buyer);
    }

}

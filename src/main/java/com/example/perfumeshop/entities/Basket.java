package com.example.perfumeshop.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "basket")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@ToString
public class Basket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_basket")
    long id;

    @Column(name = "name_of_basket")
    String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "basket_id", nullable = false)
    @ToString.Exclude
    List<Perfume> perfumes;

    @OneToOne(mappedBy = "basket", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    Buyer buyer;

    @OneToOne(mappedBy = "basket", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    Order order;

    public Basket(String name) {
        this.name = name;
    }
    public void addPerfume(Perfume perfume){
        perfumes.add(perfume);
        perfume.setBasket(this);
    }
    public void removePerfume(Perfume perfume){
        perfumes.remove(perfume);
        perfume.setBasket(null);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basket basket = (Basket) o;
        return id == basket.id && name.equals(basket.name)
                && perfumes.equals(basket.perfumes) && buyer.equals(basket.buyer)
                && order.equals(basket.order);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, perfumes, buyer, order);
    }
}

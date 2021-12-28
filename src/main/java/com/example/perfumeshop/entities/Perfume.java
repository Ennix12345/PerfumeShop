package com.example.perfumeshop.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "perfume")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@ToString
public class Perfume implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfume")
    long id;

    @Column(name = "name_of_perfume")
    String name;

    @Column(name = "brand_of_perfume")
    String brand;

    @Column(name = "perfume_by_sex")
    String sex;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "basket_id", insertable = false, updatable = false)
    @JsonIgnore
    @ToString.Exclude
    Basket basket;

    public Perfume(String name, String brand, String sex) {
        this.name = name;
        this.brand = brand;
        this.sex = sex;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Perfume perfume = (Perfume) o;
        return id == perfume.id && name.equals(perfume.name)
                && brand.equals(perfume.brand) && sex.equals(perfume.sex)
                && basket.equals(perfume.basket);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, brand, sex, basket);
    }
}

package com.example.perfumeshop.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

import java.util.Objects;

@Entity
@Table(name = "buyer")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@ToString
public class Buyer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_buyer")
    long id;

    @NotBlank(message = "поле должно содержать символы")
    @Column(name = "buyer_name")
    String name;

    @NotBlank(message = "поле должно содержать символы")
    @Email(message = "почта должна быть валидной")
    @Column(name = "buyer_mail")
    String mail;

    @NotBlank(message = "поле должно быть заполнено")
    @Column(name = "buyer_number_of_phone")
    String numberOfPhone;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "basket_id")
    @JsonIgnore
    @ToString.Exclude
    Basket basket;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    @JsonIgnore
    @ToString.Exclude
    Order order;

    public Buyer(String name, String mail, String numberOfPhone) {
        this.name = name;
        this.mail = mail;
        this.numberOfPhone = numberOfPhone;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Buyer buyer = (Buyer) o;
        return id == buyer.id && name.equals(buyer.name)
                && mail.equals(buyer.mail) && numberOfPhone.equals(buyer.numberOfPhone)
                && basket.equals(buyer.basket) && order.equals(buyer.order);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, mail, numberOfPhone, basket, order);
    }
}

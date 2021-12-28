package com.example.perfumeshop.repositories;
import com.example.perfumeshop.entities.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {

    @Query(value = "select b from Basket b where b.name =:name")
    Basket findByName(@Param("name") String name);

}

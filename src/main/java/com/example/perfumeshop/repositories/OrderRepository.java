package com.example.perfumeshop.repositories;
import com.example.perfumeshop.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

    @Query(value = "select n from Order n where n.numberOfOrder =:order")
    Order findByNumberOfOrder(@Param("order") long order);
}

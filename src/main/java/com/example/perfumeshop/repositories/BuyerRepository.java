package com.example.perfumeshop.repositories;
import com.example.perfumeshop.entities.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {

    @Query(value = "select b from Buyer b where b.name =:name")
    Buyer findByName(@Param("name") String name);

    @Query(value = "select b from Buyer b where b.numberOfPhone =:number")
    Buyer findByNumberOfPhone(@Param("number") String number);

    @Query(value = "select b from Buyer b where b.id =:id")
    Buyer findById(@Param("id") long id);

}

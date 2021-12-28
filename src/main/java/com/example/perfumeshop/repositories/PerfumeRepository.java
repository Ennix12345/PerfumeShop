package com.example.perfumeshop.repositories;
import com.example.perfumeshop.entities.Perfume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerfumeRepository extends JpaRepository<Perfume, Long> {

    @Query(value = "select p from Perfume p where p.name =:name")
    Perfume findByName(@Param("name") String name);

    @Query(value = "select p from Perfume p where p.brand in :brands")
    Perfume findByBrand(@Param("brands") String brands);

    @Query(value = "select p from Perfume p where p.sex =:sex")
    List<Perfume> findBySex(@Param("sex") String sex);
}

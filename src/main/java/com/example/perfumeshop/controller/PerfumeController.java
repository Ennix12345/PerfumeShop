package com.example.perfumeshop.controller;
import com.example.perfumeshop.entities.Basket;
import com.example.perfumeshop.entities.Buyer;
import com.example.perfumeshop.entities.Order;
import com.example.perfumeshop.entities.Perfume;
import com.example.perfumeshop.repositories.BuyerRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/perfumes")
public class PerfumeController{
    private BuyerRepository buyerRepository;

    @Autowired
    public void setBuyerRepository(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }
    @GetMapping(value = "/all")
    public ResponseEntity<List<Buyer>> getAllBuyers(){
        List<Buyer> buyers = buyerRepository.findAll();
        return ResponseEntity.ok().body(buyers);
    }
    @PostMapping(value="/save")
    public ResponseEntity<Buyer> saveBuyer(){
        Buyer buyer = new Buyer();
        buyer.setName("Ольга");
        buyer.setMail("zeep@mail.ru");
        buyer.setNumberOfPhone("0501163349");
        Basket basket = new Basket("Корзина 6");
        Perfume perfume = new Perfume("Black Heart", "Gucci", "women");
        basket.setPerfumes(Collections.singletonList(perfume));
        buyer.setBasket(basket);
        buyer.setOrder(new Order(6732115589L));

        buyerRepository.save(buyer);
        return ResponseEntity.accepted().body(buyer);
    }
    @PutMapping(value = "/edit/{id}")
    public ResponseEntity<Buyer> editBuyer(@PathVariable long id, @RequestBody(required = false) Buyer buyer){
        Buyer byId = buyerRepository.findById(id);
        byId.setMail("xxx123@gmail.com");

        final Buyer saveBuyer = buyerRepository.save(byId);
        return ResponseEntity.ok(saveBuyer);
    }
    @GetMapping(value = "/{name}")
    public ResponseEntity<Buyer> getBuyer(@PathVariable String name){
        Buyer byId = buyerRepository.findByName(name);
        return new ResponseEntity<>(byId, HttpStatus.FOUND);
    }
    @GetMapping(value = "/find/{phone}")
    public Buyer findByNumber(@PathVariable String phone){
        return buyerRepository.findByNumberOfPhone(phone);
    }
    @DeleteMapping(value = "/del/{id}")
    public void deleteBuyer(@PathVariable long id){
        Buyer buyer = buyerRepository.getById(id);
        buyerRepository.delete(buyer);
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<HttpStatus> deleteByResponse(@PathVariable long id){
        buyerRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

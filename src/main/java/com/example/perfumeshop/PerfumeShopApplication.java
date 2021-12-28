package com.example.perfumeshop;
import com.example.perfumeshop.entities.Basket;
import com.example.perfumeshop.entities.Buyer;
import com.example.perfumeshop.entities.Order;
import com.example.perfumeshop.entities.Perfume;
import com.example.perfumeshop.repositories.BasketRepository;
import com.example.perfumeshop.repositories.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class PerfumeShopApplication implements CommandLineRunner {
    private BuyerRepository buyerRepository;

    @Autowired
    public void setBuyerRepository(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(PerfumeShopApplication.class, args);
    }
    @Override
    public void run(String... args){
        Perfume perfume1 = new Perfume("Spring Blossom", "Paco Rabanne", "woman");
        Perfume perfume2 = new Perfume("Temptation", "Dior", "man");
        Perfume perfume3 = new Perfume("Water Mellon", "Givenchy", "woman");
        Perfume perfume4 = new Perfume("Deep Nature", "Calvin Klein", "man");
        Perfume perfume5 = new Perfume("Sexy Man", "Dolce&Gabbana", "man");
        Perfume perfume6 = new Perfume("Idole", "Lancome", "woman");
        Perfume perfume7 = new Perfume("Good Girl", "Carolina Herrera", "woman");
        Perfume perfume8 = new Perfume("She Wood", "Dsquard", "woman");
        Perfume perfume9 = new Perfume("Sparkls", "Dior", "man");

        Order number1 = new Order(7422219000L);
        Order number2 = new Order(1190432882L);
        Order number3 = new Order(9755660343L);
        Order number4 = new Order(6611404321L);
        Order number5 = new Order(4571230977L);

        Basket basket1 = new Basket("Корзина 1");
        basket1.setPerfumes(Arrays.asList(perfume1, perfume2));
        Basket basket2 = new Basket("Корзина 2");
        basket2.setPerfumes(Collections.singletonList(perfume3));
        Basket basket3 = new Basket("Корзина 3");
        basket3.setPerfumes(Collections.singletonList(perfume4));
        Basket basket4 = new Basket("Корзина 4");
        basket4.setPerfumes(Arrays.asList(perfume5, perfume6));
        Basket basket5 = new Basket("Корзина 5");
        basket5.setPerfumes(Arrays.asList(perfume7, perfume8, perfume9));

        Buyer buyer1 = new Buyer("Карина", "grace_di@gmail.com", "0994501133");
        buyer1.setOrder(number1);
        buyer1.setBasket(basket1);

        Buyer buyer2 = new Buyer("Лариса", "larisa707@mail.ru", "0663408989");
        buyer2.setOrder(number2);
        buyer2.setBasket(basket2);

        Buyer buyer3 = new Buyer("Александр", "mikki66@ukr.net", "0675559080");
        buyer3.setOrder(number3);
        buyer3.setBasket(basket3);

        Buyer buyer4 = new Buyer("Денис", "den_snow@gmail.com", "0508821647");
        buyer4.setOrder(number4);
        buyer4.setBasket(basket4);

        Buyer buyer5 = new Buyer("Виктория", "kira005@ukr.net", "0980046612");
        buyer5.setOrder(number5);
        buyer5.setBasket(basket5);

        buyerRepository.saveAll(Arrays.asList(buyer1, buyer2, buyer3, buyer4, buyer5));


    }
}

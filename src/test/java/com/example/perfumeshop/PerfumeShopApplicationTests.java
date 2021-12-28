package com.example.perfumeshop;
import com.example.perfumeshop.entities.Basket;
import com.example.perfumeshop.entities.Buyer;
import com.example.perfumeshop.entities.Order;
import com.example.perfumeshop.entities.Perfume;
import com.example.perfumeshop.repositories.BasketRepository;
import com.example.perfumeshop.repositories.BuyerRepository;
import com.example.perfumeshop.repositories.OrderRepository;
import com.example.perfumeshop.repositories.PerfumeRepository;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PerfumeShopApplicationTests {
    @Autowired
    BuyerRepository buyerRepository;
    @Autowired
    BasketRepository basketRepository;
    @Autowired
    PerfumeRepository perfumeRepository;
    @Autowired
    OrderRepository orderRepository;

    @Test
    void findBuyerByName(){
        Buyer buyer = buyerRepository.findByName("Александр");
        System.out.println(buyer.getMail());
        System.out.println(buyer.getBasket());

        assertThat(buyer.getMail()).isEqualTo("mikki66@ukr.net");
    }
    @Test
    void findBuyerByNumberOfPhone(){
        Buyer buyer = buyerRepository.findByNumberOfPhone("0980046612");
        System.out.println(buyer.getName());
        System.out.println(buyer.getOrder());

        assertThat(buyer.getName()).isEqualTo("Виктория");
        assertThat(buyer.getBasket()).hasNoNullFieldsOrProperties();
    }
    @Test
    void deleteBuyer(){
        Buyer buyer = buyerRepository.findByName("Карина");
        buyerRepository.delete(buyer);

        List<Buyer> all = buyerRepository.findAll();
        assertThat(all).hasSize(4);

        List<Order> allNum = orderRepository.findAll();
        assertThat(allNum).hasSize(4);

        List<Basket> allBas = basketRepository.findAll();
        assertThat(allBas).hasSize(4);
    }
    @Test
    void findBasketByName(){
        Basket name = basketRepository.findByName("Корзина 4");
        System.out.println(name.getOrder());
        System.out.println(name.getBuyer());

        Condition<Buyer> condition = new Condition<>(
                b->b.getName().equalsIgnoreCase("денис"), "condition");
        assertThat(name.getBuyer()).is(condition);
    }
    @Test
    void deleteBasket(){
        Basket basket = basketRepository.findByName("Корзина 5");
        basketRepository.delete(basket);

        List<Basket> all = basketRepository.findAll();
        assertThat(all).hasSize(4);

        List<Buyer> allBuyers = buyerRepository.findAll();
        assertThat(allBuyers).hasSize(4);

        List<Order> allNumbers = orderRepository.findAll();
        assertThat(allNumbers).hasSize(4);

        List<Perfume> allPerfumes = perfumeRepository.findAll();
        assertThat(allPerfumes).hasSize(6);
    }
    @Test
    void findNumberOfOrder() {
        Order number = orderRepository.findByNumberOfOrder(1190432882L);
        System.out.println(number.getBuyer());
        System.out.println(number.getBasket());

        assertThat(number.getNumberOfOrder()).isNotZero();
    }
    @Test
    void deleteOrder(){
        Order byNumberOfOrder = orderRepository.findByNumberOfOrder(6611404321L);
        orderRepository.delete(byNumberOfOrder);

        List<Order> all = orderRepository.findAll();
        assertThat(all).hasSize(4);

        List<Basket> allBaskets = basketRepository.findAll();
        assertThat(allBaskets).hasSize(4);

        List<Buyer> allBuyers = buyerRepository.findAll();
        assertThat(allBuyers).hasSize(4);
    }
    @Test
    void findPerfumeByName(){
        Perfume perfume = perfumeRepository.findByName("Deep Nature");
        System.out.println(perfume.getBrand());
        System.out.println(perfume.getBasket().getBuyer());

        assertThat(perfume.getSex()).isEqualTo("man");
        assertThat(perfume.getName()).isMixedCase();

        List<Perfume> all = perfumeRepository.findAll();
        assertThat(all.get(4)).isIn(all);
    }
    @Test
    void findPerfumeByBrand(){
        Perfume perfume = perfumeRepository.findByBrand("Carolina Herrera");
        System.out.println(perfume.getName());
        System.out.println(perfume.getBasket().getOrder());

        assertThat(perfume.getSex()).isEqualTo("woman");

        List<Perfume> all = perfumeRepository.findAll();
        assertThat(all.get(7)).isIn(all);
    }
    @Test
    void findPerfumeBySex(){
        List<Perfume> man1 = perfumeRepository.findBySex("woman");
        assertThat(man1).hasSizeGreaterThanOrEqualTo(4);

        List<Perfume> man2 = perfumeRepository.findBySex("man");
        assertThat(man2).hasSizeLessThanOrEqualTo(4);
    }
    @Test
    void deletePerfume(){
        Perfume perfume = perfumeRepository.findByName("Good Girl");
        perfume.getBasket().setPerfumes(null);
        perfumeRepository.delete(perfume);

        List<Perfume> all = perfumeRepository.findAll();
        assertThat(all).hasSize(7);

        List<Basket> allBaskets = basketRepository.findAll();
        assertThat(allBaskets).hasSize(5);

    }
}

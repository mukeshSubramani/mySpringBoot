package org.example.test.repoTests;

import org.example.domains.Ingredient;
import org.example.domains.Taco;
import org.example.domains.TacoOrder;
import org.example.repo.OrderRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class OrderRepoTests {

    @Autowired
    OrderRepo orderRepo;


    @Test
    void insertAndAssert(){
       TacoOrder tacoOrder =  orderRepo.save(getTacoOrder());
       assertThat(tacoOrder.getId()).isPositive();

    }

    @Test
    void testDeliveryZip(){
        orderRepo.save(getTacoOrder());
        List<TacoOrder> tacoOrderList = orderRepo.findByDeliveryZip("1234");
        assertEquals("1234",tacoOrderList.get(0).getDeliveryZip());
    }

    @Test
    void testDeliveryAndPlacedAt(){
        Date date = new Date();
        orderRepo.save(getTacoOrder());
        Date date1 = new Date();

        List<TacoOrder> tacoOrderList =
                orderRepo.readOrdersByDeliveryZipAndPlacedAtBetween("1234",date,date1);
        assertEquals("1234",tacoOrderList.get(0).getDeliveryZip());
    }

    private TacoOrder getTacoOrder(){
        TacoOrder tacoOrder = new TacoOrder();
        tacoOrder.setPlacedAt(new Date());
        tacoOrder.setCcCVV("111");
        tacoOrder.setDeliveryCity("LND");
        tacoOrder.setDeliveryName("delivery");
        tacoOrder.setDeliveryState("LDN");
        tacoOrder.setDeliveryZip("1234");
        tacoOrder.setDeliveryStreet("street");
        tacoOrder.setCcExpiration("01/01");
        tacoOrder.setCcNumber("12314");
        tacoOrder.setCcCVV("111");

        Taco taco1 = new Taco();
        taco1.setNameTaco("Taco One");
        taco1.addIngredient(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
        taco1.addIngredient(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
        taco1.addIngredient(new Ingredient("CHED", "Shredded Cheddar", Ingredient.Type.CHEESE));
        taco1.setCreatedAt(new Date());
        tacoOrder.addTaco(taco1);
        Taco taco2 = new Taco();
        taco2.setNameTaco("Taco Two");
        taco2.addIngredient(new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
        taco2.addIngredient(new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));
        taco2.addIngredient(new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE));
        taco2.setCreatedAt(new Date());
        tacoOrder.addTaco(taco2);

        return tacoOrder;
    }
}

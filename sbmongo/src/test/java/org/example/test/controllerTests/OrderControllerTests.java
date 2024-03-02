package org.example.test.controllerTests;

import org.example.controller.OrderController;
import org.example.domains.Ingredient;
import org.example.domains.Taco;
import org.example.domains.TacoOrder;
import org.example.repo.IngredientRepo;
import org.example.repo.OrderRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    OrderRepo orderRepo;

    @MockBean
    IngredientRepo ingredientRepo;

    @Test
    void showOrderPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/orders/current")
                        .flashAttr( "tacoOrder", new TacoOrder()))
                .andExpect(status().isOk())
                .andExpect(view().name("orderForm"));
    }

    @Test
    void orderPostMethodTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                .flashAttr("tacoOrder",getTacoOrder()))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location","/"));

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


    private List<Taco> getTacoList(){
        List<Taco> t = new ArrayList<>();

        Taco taco1 = new Taco();
        taco1.setNameTaco("Taco One");
        taco1.addIngredient(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
        taco1.addIngredient(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
        taco1.addIngredient(new Ingredient("CHED", "Shredded Cheddar", Ingredient.Type.CHEESE));
        taco1.setCreatedAt(new Date());
        t.add(taco1);
        Taco taco2 = new Taco();
        taco2.setNameTaco("Taco Two");
        taco2.addIngredient(new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
        taco2.addIngredient(new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));
        taco2.addIngredient(new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE));
        taco2.setCreatedAt(new Date());
        t.add(taco2);


        return t;
    }
}

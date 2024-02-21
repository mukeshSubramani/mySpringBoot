package org.example.test.controllerTests;

import org.example.controller.DesignTacoController;
import org.example.domains.Ingredient;
import org.example.domains.Taco;
import org.example.domains.TacoOrder;
import org.example.repo.IngredientRepo;
import org.example.repo.OrderRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DesignTacoController.class)
class DesignContollerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    IngredientRepo ingredientRepo;

    @MockBean
    OrderRepo orderRepo;

    private List<Ingredient> ingredients;

    private Taco design;

    @BeforeEach
    public void setup() {
        ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
        );

        when(ingredientRepo.findAll())
                .thenReturn(ingredients);

    }

    @Test
    void checkDesingPageStartup() throws Exception {
        HashMap<String,Object> sessionAttr = new HashMap<>();
        sessionAttr.put("tacoOrder","xxx");
        mockMvc.perform(MockMvcRequestBuilders.get("/design"))
                .andExpect(status().isOk())
                .andExpect(view().name("design"))
                .andExpect(model().attribute("wrap",ingredients.subList(0,2)))
                .andExpect(model().attribute("taco", new Taco()))
                .andExpect(model().attribute("tacoOrder",new TacoOrder()));
    }

    @Test
    void checkPostmethod() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/design")
                .content("nameTaco=Test+first&ingredientListTaco=FLTO,GRBF,CHED")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().stringValues("Location","/orders/current"));
    }
}

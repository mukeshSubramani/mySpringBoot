package org.example.test.controllerTests;

import org.example.controller.HomeController;
import org.example.repo.IngredientRepo;
import org.example.repo.OrderRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(HomeController.class)
class HomeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    OrderRepo orderRepo;

    @MockBean
    IngredientRepo ingredientRepo;

    @Test
    void testHomePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(content().string(containsString("Welcome")));
    }

}

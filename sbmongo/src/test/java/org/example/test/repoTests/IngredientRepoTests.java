package org.example.test.repoTests;

import org.example.domains.Ingredient;
import org.example.repo.IngredientRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class IngredientRepoTests {

    @Autowired
    IngredientRepo ingredientRepo;

    @Test
    void assertIngregientRepo(){
        //data is load using application runner during app startup
        Optional<Ingredient> ingredient = ingredientRepo.findById("FLTO");
        assertThat(ingredient).isPresent();
    }

    @Test
    void testCustomQuery(){
        List<Ingredient> ingredients = ingredientRepo.getIngredientbyName("Flour Tortilla");
        assertEquals("Flour Tortilla", ingredients.get(0).getName());
    }
}

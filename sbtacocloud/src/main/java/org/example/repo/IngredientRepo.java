package org.example.repo;

import org.example.domains.Ingredient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface IngredientRepo extends CrudRepository<Ingredient, String> {

    @Query("Select o from Ingredient o Where o.name = ?1")
    List<Ingredient> getIngredientbyName(String name);

}

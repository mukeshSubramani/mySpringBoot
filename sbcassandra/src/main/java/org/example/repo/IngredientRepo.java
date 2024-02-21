package org.example.repo;

import org.example.domains.Ingredient;
import org.springframework.data.repository.CrudRepository;


public interface IngredientRepo extends CrudRepository<Ingredient, String> {

//    @Query("Select o from Ingredient o Where o.name = ?1")
//    List<Ingredient> getIngredientbyName(String name);

}

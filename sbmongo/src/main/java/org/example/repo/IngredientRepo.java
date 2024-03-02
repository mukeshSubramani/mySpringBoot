package org.example.repo;

import org.example.domains.Ingredient;
import org.springframework.data.repository.CrudRepository;


public interface IngredientRepo extends CrudRepository<Ingredient, String> {


}

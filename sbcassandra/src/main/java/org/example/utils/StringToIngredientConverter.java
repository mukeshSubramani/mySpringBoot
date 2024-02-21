package org.example.utils;

import org.example.domains.Ingredient;
import org.example.domains.IngredientUTD;
import org.example.repo.IngredientRepo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StringToIngredientConverter implements Converter<String, IngredientUTD> {

  private IngredientRepo ingredientRepository;

  public StringToIngredientConverter(IngredientRepo ingredientRepository) {
    this.ingredientRepository = ingredientRepository;
  }
  
  @Override
  public IngredientUTD convert(String id) {
    Optional<Ingredient> ingredient = ingredientRepository.findById(id);
    if (ingredient.isEmpty()) {
      return null;
    }
    
    return ingredient.map(i -> {
      return new IngredientUTD(i.getName(), i.getType());
    }).get();
  }
  
}

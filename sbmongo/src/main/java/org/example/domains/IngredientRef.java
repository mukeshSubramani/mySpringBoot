package org.example.domains;

import lombok.Data;

@Data
public class IngredientRef {

    private Long id;
    private final String ingredient;
}

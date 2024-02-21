package org.example.domains;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@Data
@UserDefinedType("ingredient")
public class IngredientUTD {

    private final String name;
    private final Ingredient.Type type;
}

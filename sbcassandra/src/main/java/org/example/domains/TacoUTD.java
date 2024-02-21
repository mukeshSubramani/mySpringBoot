package org.example.domains;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.List;

@Data
@UserDefinedType("tacos")
public class TacoUTD {

    private String nameTaco;

    private List<IngredientUTD> ingredientListTaco ;

    public TacoUTD(String nameTaco, List<IngredientUTD> ingredientListTaco) {
        this.nameTaco = nameTaco;
        this.ingredientListTaco = ingredientListTaco;
    }
}

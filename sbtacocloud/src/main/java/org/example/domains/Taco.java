package org.example.domains;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min=5, message = "Name must be at least 5 characters long")
    private String nameTaco;

    private Date createdAt;

    @NotNull
    @Size(min = 1, message = "You must choose atlease 1 ingredents")
    @ManyToMany(cascade = CascadeType.ALL)
    private List<IngredientRef> ingredientListTaco = new ArrayList<>();

    public void addIngredient(Ingredient taco) {
        this.ingredientListTaco.add(new IngredientRef(taco.getId()));
    }

}

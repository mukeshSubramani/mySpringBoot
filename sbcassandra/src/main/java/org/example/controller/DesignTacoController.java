package org.example.controller;

import lombok.extern.log4j.Log4j2;
import org.example.domains.Ingredient;
import org.example.domains.Ingredient.Type;
import org.example.domains.Taco;
import org.example.domains.TacoOrder;
import org.example.repo.IngredientRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/design")
@Log4j2
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientRepo ingredientRepo;

    public DesignTacoController(IngredientRepo ingredientRepo){
        this.ingredientRepo = ingredientRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model){
        Iterable<Ingredient> ingredientListIterable = ingredientRepo.findAll();
        List<Ingredient> ingredientListDesign = new ArrayList<>();
        ingredientListIterable.forEach(ingredientListDesign::add);
        Type[] types = Ingredient.Type.values();
        for(Type type: types){
            model.addAttribute(type.toString().toLowerCase(),filterByType(ingredientListDesign,type));
        }

    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type){
        return ingredients.stream().filter(x -> x.getType().equals(type)).toList();
    }
    
    @ModelAttribute(name = "tacoOrder")
    public TacoOrder tacoOrder(){
        return  new TacoOrder();
    }
    
    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }
    
    @GetMapping
    public String showDesignForm(){
        return "design";
    }

    @PostMapping
    public String processTaco( Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder){
        if(errors.hasErrors()){
            log.info("Taco object validation failed {}" ,errors.getAllErrors());
            return "design";
        }
        taco.setCreatedAt(new Date());
        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);
        return "redirect:/orders/current";
    }
}

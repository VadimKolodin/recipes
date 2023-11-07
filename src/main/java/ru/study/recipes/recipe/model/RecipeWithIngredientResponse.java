package ru.study.recipes.recipe.model;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class RecipeWithIngredientResponse {

    private UUID id;

    private String name;

    private String description;

    private List<IngredientInRecipeResponse> ingredients;

}

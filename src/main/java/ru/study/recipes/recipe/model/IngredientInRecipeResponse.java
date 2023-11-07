package ru.study.recipes.recipe.model;

import lombok.Data;

import java.util.UUID;

@Data
public class IngredientInRecipeResponse {

    private UUID id;

    private String name;

    private Integer quantity;

    private String measure;

}

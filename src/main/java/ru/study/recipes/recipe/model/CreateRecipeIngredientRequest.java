package ru.study.recipes.recipe.model;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateRecipeIngredientRequest {

    private UUID ingredientId;

    private Integer quantity;

    private String measure;

}

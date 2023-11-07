package ru.study.recipes.recipe.model;

import lombok.Data;

import java.util.List;

@Data
public class CreateRecipeRequest {

    private String name;

    private String description;

    private List<CreateRecipeIngredientRequest> ingredients;

}

package ru.study.recipes.ingredient.model;

import lombok.Data;
import ru.study.recipes.data.ingredient.IngredientCategory;

@Data
public class CreateIngredientRequest {

    private String name;

    private IngredientCategory category;

}

package ru.study.recipes.ingredient.model;

import lombok.Data;
import ru.study.recipes.data.ingredient.IngredientCategory;

import java.util.UUID;

@Data
public class IngredientResponse {

    private UUID id;

    private String name;

    private IngredientCategory category;

}

package ru.study.recipes.data.recipe.model;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class IngredientInRecipeView {

    private final UUID id;

    private final String name;

    private final UUID recipeId;

    private final Integer quantity;

    private final String measure;

}

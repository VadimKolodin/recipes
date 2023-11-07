package ru.study.recipes.ingredient.model;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import ru.study.recipes.data.ingredient.IngredientCategory;

@Data
public class CreateIngredientRequest {

    private String name;

    private IngredientCategory category;

}

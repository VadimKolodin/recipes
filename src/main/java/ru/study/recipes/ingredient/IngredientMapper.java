package ru.study.recipes.ingredient;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.study.recipes.data.ingredient.IngredientEntity;
import ru.study.recipes.data.recipe.model.IngredientInRecipeView;
import ru.study.recipes.ingredient.model.CreateIngredientRequest;
import ru.study.recipes.ingredient.model.IngredientResponse;
import ru.study.recipes.recipe.model.IngredientInRecipeResponse;

@Mapper(componentModel = "spring")
public interface IngredientMapper {

    IngredientInRecipeResponse map(IngredientInRecipeView ingredient);

    IngredientResponse map(IngredientEntity entity);

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    IngredientEntity map(CreateIngredientRequest request);

}

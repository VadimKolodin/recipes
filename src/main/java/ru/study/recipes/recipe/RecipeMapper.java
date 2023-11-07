package ru.study.recipes.recipe;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.study.recipes.data.recipe.RecipeEntity;
import ru.study.recipes.data.recipe.RecipeToIngredientEntity;
import ru.study.recipes.data.recipe.RecipeToIngredientEntityId;
import ru.study.recipes.recipe.model.CreateRecipeIngredientRequest;
import ru.study.recipes.recipe.model.CreateRecipeRequest;
import ru.study.recipes.recipe.model.IngredientInRecipeResponse;
import ru.study.recipes.recipe.model.RecipeWithIngredientResponse;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "cdi")
public interface RecipeMapper {


    RecipeWithIngredientResponse map(RecipeEntity recipe, List<IngredientInRecipeResponse> ingredients);


    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    RecipeEntity mapToEntity(CreateRecipeRequest request);

    @Mapping(target = "id.recipeId", source = "recipeId")
    @Mapping(target = "id.ingredientId", source = "request.ingredientId")
    RecipeToIngredientEntity mapToEntity(UUID recipeId, CreateRecipeIngredientRequest request);


}

package ru.study.recipes.recipe;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import ru.study.recipes.data.recipe.RecipeEntity;
import ru.study.recipes.data.recipe.RecipeRepository;
import ru.study.recipes.data.recipe.RecipeToIngredientEntity;
import ru.study.recipes.data.recipe.model.IngredientInRecipeView;
import ru.study.recipes.ingredient.IngredientMapper;
import ru.study.recipes.recipe.model.CreateRecipeRequest;
import ru.study.recipes.recipe.model.RecipeWithIngredientResponse;
import ru.study.recipes.data.recipe.RecipeToIngredientRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Stateless
public class RecipeService {

    @Inject
    private IngredientMapper ingredientMapper;

    @Inject
    private RecipeMapper recipeMapper;

    @Inject
    private RecipeRepository recipeRepository;

    @Inject
    private RecipeToIngredientRepository recipeToIngredientRepository;


    public List<RecipeWithIngredientResponse> getAll() {

        List<RecipeEntity> recipes = recipeRepository.findAll();
        List<IngredientInRecipeView> ingredients = recipeToIngredientRepository.findAllByRecipeIds(recipes.stream()
                                                                                                  .map(RecipeEntity::getId)
                                                                                                  .toList());

        Map<UUID, List<IngredientInRecipeView>> recipeToIngredients = ingredients.stream()
                                                                                 .collect(Collectors.groupingBy(
                                                                                     IngredientInRecipeView::getRecipeId,
                                                                                     Collectors.toList()));

        return recipes.stream()
                      .map(r -> recipeMapper.map(r,
                                                 recipeToIngredients.getOrDefault(r.getId(), Collections.emptyList())
                                                                    .stream()
                                                                    .map(ingredientMapper::map)
                                                                    .toList()))
                      .toList();
    }

    @Transactional
    public UUID create(CreateRecipeRequest createRecipeRequest) {

        RecipeEntity recipe = recipeMapper.mapToEntity(createRecipeRequest);

        recipeRepository.persist(recipe);

        List<RecipeToIngredientEntity> recipeToIngredientEntities = createRecipeRequest.getIngredients()
                                                                                       .stream()
                                                                                       .map(i -> recipeMapper.mapToEntity(
                                                                                           recipe.getId(),
                                                                                           i))
                                                                                       .toList();
        recipeToIngredientEntities.forEach(recipeToIngredientRepository::persist);

        return recipe.getId();
    }

    @Transactional
    public void delete(UUID recipeId) {
        recipeToIngredientRepository.deleteByRecipeId(recipeId);
        recipeRepository.delete(recipeId);
    }

}

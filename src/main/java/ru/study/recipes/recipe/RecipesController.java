package ru.study.recipes.recipe;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.study.recipes.recipe.model.CreateRecipeRequest;
import ru.study.recipes.recipe.model.RecipeWithIngredientResponse;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class RecipesController {

    private final RecipeService recipeService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<RecipeWithIngredientResponse> getAll() {
        return recipeService.getAll();
    }

    @GetMapping(value = "/{recipeId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public RecipeWithIngredientResponse get(@PathVariable UUID recipeId) {
        return recipeService.get(recipeId);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public void create(@RequestBody CreateRecipeRequest createRecipeRequest) {
        recipeService.create(createRecipeRequest);
    }

    @DeleteMapping("/{recipeId}")
    public void delete(@PathVariable UUID recipeId) {
        recipeService.delete(recipeId);
    }

}
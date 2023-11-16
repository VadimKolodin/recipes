package ru.study.recipes.ingredient;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.study.recipes.ingredient.model.CreateIngredientRequest;
import ru.study.recipes.ingredient.model.IngredientResponse;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<IngredientResponse> getAll() {
        return ingredientService.getAll();
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public void create(@RequestBody CreateIngredientRequest createIngredientRequest) {
        ingredientService.create(createIngredientRequest);
    }

    @DeleteMapping("/{ingredientId}")
    public void delete(@PathVariable UUID ingredientId) {
        ingredientService.delete(ingredientId);
    }

}
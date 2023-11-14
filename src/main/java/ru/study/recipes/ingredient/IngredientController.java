package ru.study.recipes.ingredient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.study.recipes.ingredient.model.CreateIngredientRequest;
import ru.study.recipes.recipe.RecipeService;
import ru.study.recipes.recipe.model.CreateRecipeRequest;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class IngredientController {


    private final ObjectMapper objectMapper;

    private final IngredientService ingredientService;

    @GetMapping("/ingredients")
    @SneakyThrows
    public ResponseEntity<String> getAll() {
        return new ResponseEntity<>(objectMapper.writeValueAsString(ingredientService.getAll()), HttpStatus.OK);
    }

    @PostMapping("/ingredients")
    @SneakyThrows
    public ResponseEntity create(@RequestBody String createIngredientRequestString) {

        CreateIngredientRequest createIngredientRequest = objectMapper.readValue(createIngredientRequestString,
                                                                             new TypeReference<>() {});
        ingredientService.create(createIngredientRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/ingredients/{ingredientIdString}")
    public ResponseEntity delete(@PathVariable String ingredientIdString) {
        UUID ingredientId = UUID.fromString(ingredientIdString);
        ingredientService.delete(ingredientId);
        return new ResponseEntity(HttpStatus.OK);
    }

}
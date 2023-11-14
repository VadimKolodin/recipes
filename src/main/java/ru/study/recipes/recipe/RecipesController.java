package ru.study.recipes.recipe;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
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

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class RecipesController {

    private final ObjectMapper objectMapper;

    private final RecipeService recipeService;

    @GetMapping("/recipes")
    @SneakyThrows
    public ResponseEntity<String> getAll() {
        return new ResponseEntity<>(objectMapper.writeValueAsString(recipeService.getAll()), HttpStatus.OK);
    }

    @PostMapping("/recipes")
    @SneakyThrows
    public ResponseEntity create(@RequestBody String createRecipeRequestString) {

        CreateRecipeRequest createRecipeRequest = objectMapper.readValue(createRecipeRequestString,
                                                                         new TypeReference<CreateRecipeRequest>() {});
        recipeService.create(createRecipeRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/recipes/{recipeIdString}")
    public ResponseEntity delete(@PathVariable String recipeIdString) {
        UUID recipeId = UUID.fromString(recipeIdString);
        recipeService.delete(recipeId);
        return new ResponseEntity(HttpStatus.OK);
    }

}
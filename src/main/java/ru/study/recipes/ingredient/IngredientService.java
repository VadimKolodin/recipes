package ru.study.recipes.ingredient;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import ru.study.recipes.data.ingredient.IngredientEntity;
import ru.study.recipes.data.ingredient.IngredientRepository;
import ru.study.recipes.ingredient.model.CreateIngredientRequest;
import ru.study.recipes.ingredient.model.IngredientResponse;

import java.util.List;
import java.util.UUID;

@Stateless
public class IngredientService {

    @Inject
    private IngredientMapper ingredientMapper;

    @Inject
    private IngredientRepository ingredientRepository;


    public List<IngredientResponse> getAll() {
        return ingredientRepository.findAll().stream().map(ingredientMapper::map).toList();
    }

    public UUID create(CreateIngredientRequest createIngredientRequest) {
        IngredientEntity ingredient = ingredientMapper.map(createIngredientRequest);
        ingredientRepository.persist(ingredient);
        return ingredient.getId();
    }

    public void delete(UUID ingredientId) {
        ingredientRepository.delete(ingredientId);
    }

}

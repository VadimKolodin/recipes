package ru.study.recipes.ingredient;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.study.recipes.data.ingredient.IngredientEntity;
import ru.study.recipes.data.ingredient.IngredientRepository;
import ru.study.recipes.ingredient.model.CreateIngredientRequest;
import ru.study.recipes.ingredient.model.IngredientResponse;
import ru.study.recipes.messaging.EventLogger;
import ru.study.recipes.messaging.model.AuditEvent;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final EventLogger eventLogger;

    private final IngredientMapper ingredientMapper;

    private final IngredientRepository ingredientRepository;


    public List<IngredientResponse> getAll() {
        return ingredientRepository.findAll().stream().map(ingredientMapper::map).toList();
    }

    @Transactional
    public UUID create(CreateIngredientRequest createIngredientRequest) {
        IngredientEntity ingredient = ingredientMapper.map(createIngredientRequest);
        ingredientRepository.persist(ingredient);
        eventLogger.log(ingredient, AuditEvent.CREATE);
        return ingredient.getId();
    }

    @Transactional
    public void delete(UUID ingredientId) {
        IngredientEntity deleted = ingredientRepository.delete(ingredientId);
        eventLogger.log(deleted, AuditEvent.DELETE);
    }

}

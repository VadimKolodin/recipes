package ru.study.recipes.messaging;

import jakarta.persistence.Entity;
import ru.study.recipes.data.ingredient.IngredientEntity;
import ru.study.recipes.data.recipe.RecipeEntity;
import ru.study.recipes.messaging.model.AuditEvent;

public interface EventLogger {

    void log(Object entity, AuditEvent event);

}

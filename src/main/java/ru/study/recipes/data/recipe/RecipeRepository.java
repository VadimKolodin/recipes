package ru.study.recipes.data.recipe;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ru.study.recipes.data.recipe.RecipeEntity;

import java.util.List;
import java.util.UUID;

@Stateless
public class RecipeRepository {

    @PersistenceContext
    EntityManager em;

    public void persist(RecipeEntity entity) {
        em.persist(entity);
    }

    public List<RecipeEntity> findAll() {
        String q = "select r from RecipeEntity r";
        return em.createQuery(q, RecipeEntity.class).getResultList();
    }

    public void delete(UUID id) {
        RecipeEntity recipeEntity = em.find(RecipeEntity.class, id);
        em.remove(recipeEntity);
    }

}

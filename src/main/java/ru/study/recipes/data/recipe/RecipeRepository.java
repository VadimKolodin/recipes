package ru.study.recipes.data.recipe;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class RecipeRepository {

    @PersistenceContext
    private EntityManager em;

    public void persist(RecipeEntity entity) {
        em.persist(entity);
    }

    public RecipeEntity findById(UUID recipeId) {
        return em.find(RecipeEntity.class, recipeId);
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

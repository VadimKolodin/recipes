package ru.study.recipes.data.ingredient;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class IngredientRepository {

    @PersistenceContext
    private EntityManager em;

    public List<IngredientEntity> findAll() {
        return em.createQuery("select i from IngredientEntity i", IngredientEntity.class).getResultList();
    }

    public void persist(IngredientEntity entity) {
        em.persist(entity);
    }

    public void delete(UUID ingredientId) {
        IngredientEntity entity = em.find(IngredientEntity.class, ingredientId);
        em.remove(entity);
    }

}

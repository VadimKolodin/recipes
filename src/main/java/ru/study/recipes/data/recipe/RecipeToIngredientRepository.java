package ru.study.recipes.data.recipe;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.study.recipes.data.recipe.model.IngredientInRecipeView;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public class RecipeToIngredientRepository {

    @PersistenceContext
    private EntityManager em;

    public void persist(RecipeToIngredientEntity entity) {
        em.persist(entity);
    }

    public List<IngredientInRecipeView> findAllByRecipeIds(Collection<UUID> recipeIds) {
        String q = """
                   select new ru.study.recipes.data.recipe.model.IngredientInRecipeView(
                     i.id,
                     i.name,
                     rti.id.recipeId,
                     rti.quantity,
                     rti.measure
                   )
                   from RecipeToIngredientEntity rti
                   inner join IngredientEntity i
                     on rti.id.ingredientId = i.id
                   where rti.id.recipeId in :recipeIds
                   """;
        return em.createQuery(q, IngredientInRecipeView.class).setParameter("recipeIds", recipeIds).getResultList();
    }

    public void deleteByRecipeId(UUID recipeId) {
        String q = """
                   delete from RecipeToIngredientEntity rti
                   where rti.id.recipeId = :recipeId
                   """;
        em.createQuery(q).setParameter("recipeId", recipeId).executeUpdate();
    }

}

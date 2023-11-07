package ru.study.recipes.recipe;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.study.recipes.recipe.model.CreateRecipeRequest;
import ru.study.recipes.util.ObjectMapperFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.UUID;

@WebServlet(name = "recipesServlet", value = "/recipes/*")
public class RecipesServlet extends HttpServlet {

    private ObjectMapper objectMapper = ObjectMapperFactory.json();

    @Inject
    private RecipeService recipeService;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/json");

        try (PrintWriter out = response.getWriter()) {
            out.print(objectMapper.writeValueAsString(recipeService.getAll()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try (InputStream is = req.getInputStream()) {
            CreateRecipeRequest createRecipeRequest = objectMapper.readValue(is, new TypeReference<>() {});
            recipeService.create(createRecipeRequest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        UUID recipeId = UUID.fromString(req.getPathInfo().substring(1));
        recipeService.delete(recipeId);
    }

}
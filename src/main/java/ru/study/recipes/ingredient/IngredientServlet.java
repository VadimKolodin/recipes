package ru.study.recipes.ingredient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.study.recipes.ingredient.model.CreateIngredientRequest;
import ru.study.recipes.util.ObjectMapperFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.UUID;

@WebServlet(name = "ingredientsServlet", value = "/ingredients/*")
public class IngredientServlet extends HttpServlet {

    private ObjectMapper objectMapper = ObjectMapperFactory.json();

    @Inject
    private IngredientService ingredientService;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/json");

        try (PrintWriter out = response.getWriter()) {
            out.print(objectMapper.writeValueAsString(ingredientService.getAll()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try (InputStream is = req.getInputStream()) {
            CreateIngredientRequest createIngredientRequest = objectMapper.readValue(is, new TypeReference<>() {});
            ingredientService.create(createIngredientRequest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        UUID ingredientId = UUID.fromString(req.getPathInfo().substring(1));
        ingredientService.delete(ingredientId);
    }

}
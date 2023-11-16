package ru.study.recipes.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.study.recipes.dom.ObjectToDomTransformer;
import ru.study.recipes.ingredient.IngredientService;
import ru.study.recipes.ingredient.model.IngredientResponse;
import ru.study.recipes.recipe.RecipeService;
import ru.study.recipes.recipe.model.RecipeWithIngredientResponse;

import java.util.List;
import java.util.UUID;
import javax.xml.parsers.DocumentBuilderFactory;

@Controller
@RequiredArgsConstructor
public class XsltController {

    private final RecipeService recipeService;

    private final IngredientService ingredientService;

    @RequestMapping(value = "/xslt/recipes")
    public String getAllRecipes(Model model) throws Exception {

        List<RecipeWithIngredientResponse> recipes = recipeService.getAll();

        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element root = document.createElement("recipes");

        ObjectToDomTransformer marshaller = new ObjectToDomTransformer(document);
        for (RecipeWithIngredientResponse recipe : recipes) {

            marshaller.transform(root, recipe, "recipe");
        }

        model.addAttribute("recipes", root);
        return "recipes";
    }

    @RequestMapping(value = "/xslt/recipes/{recipeId}")
    public String getRecipe(Model model, @PathVariable UUID recipeId) throws Exception {

        RecipeWithIngredientResponse recipe = recipeService.get(recipeId);

        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element root = document.createElement("recipes");

        ObjectToDomTransformer marshaller = new ObjectToDomTransformer(document);

        marshaller.transform(root, recipe, "recipe");

        model.addAttribute("recipe", root);
        return "recipe";
    }

    @RequestMapping(value = "/xslt/ingredients")
    public String getIngredients(Model model) throws Exception {

        List<IngredientResponse> ingredients = ingredientService.getAll();

        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element root = document.createElement("ingredients");

        ObjectToDomTransformer marshaller = new ObjectToDomTransformer(document);
        for (IngredientResponse ingredient : ingredients) {

            marshaller.transform(root, ingredient, "ingredient");
        }

        model.addAttribute("ingredients", root);
        return "ingredients";
    }

}

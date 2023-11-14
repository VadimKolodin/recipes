package ru.study.recipes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import ru.study.recipes.recipe.RecipesController;

@SpringBootApplication(scanBasePackages = "ru.study.recipes")
public class RecipesApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecipesApplication.class, args);
    }

}

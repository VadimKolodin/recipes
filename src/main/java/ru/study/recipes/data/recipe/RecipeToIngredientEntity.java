package ru.study.recipes.data.recipe;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recipes_ingredients")
public class RecipeToIngredientEntity {

    @EmbeddedId
    @Column(name = "id")
    private RecipeToIngredientEntityId id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "measure")
    private String measure;

}

package co.uk.recipe.group.service;

import co.uk.recipe.group.domain.Recipe;

import java.util.Optional;
import java.util.UUID;

public interface RecipeService {
    Recipe addRecipe(Recipe recipe);

    Optional<Recipe> getRecipe(UUID recipeId);

    Iterable<Recipe> getAllRecipes();

}

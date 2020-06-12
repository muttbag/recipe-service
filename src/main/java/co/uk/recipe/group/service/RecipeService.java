package co.uk.recipe.group.service;

import co.uk.recipe.group.domain.Recipe;
import co.uk.recipe.group.message.CreateRecipeRequest;

public interface RecipeService {
    Recipe addRecipe(Recipe recipe);

    Recipe getRecipe(String recipeId);

}

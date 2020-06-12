package co.uk.recipe.group.dao;

import co.uk.recipe.group.domain.Recipe;

public interface DataSource {

    Recipe addRecipe(Recipe recipeRequest);

    Recipe getRecipe(Integer recipeId);
}

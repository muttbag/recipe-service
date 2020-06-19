package co.uk.recipe.group.service;

import co.uk.recipe.group.domain.Recipe;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public interface RecipeService {
    Recipe addRecipe(Recipe recipe, MultipartFile multipartFile) throws IOException;

    Optional<Recipe> getRecipe(UUID recipeId);

    Iterable<Recipe> getAllRecipes();

}

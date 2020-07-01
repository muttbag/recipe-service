package co.uk.recipe.group.service;

import co.uk.recipe.group.dao.RecipesRepository;
import co.uk.recipe.group.domain.Recipe;
import co.uk.recipe.group.error.RecipeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    RecipesRepository recipesRepository;

    @Autowired
    ImageService imageService;

    @Override
    public Recipe addRecipe(Recipe recipe, MultipartFile multipartFile) throws IOException {

        final String imageUrl = imageService.uploadImageToBucket(multipartFile);
        recipe.setImage(imageUrl);

        final Recipe createdRecipe = recipesRepository.save(recipe);

        return createdRecipe;
    }

    @Override
    public Optional<Recipe> getRecipe(UUID recipeId) {
        return recipesRepository.findById(recipeId.toString());
    }

    @Override
    public Iterable<Recipe> getAllRecipes() {
        return recipesRepository.findAll();
    }

    @Override
    public Recipe updateRecipe(final UUID recipeId, final Recipe updateRecipe) {

        //TODO: Check if the reicpe always exists, is this necessary?
        //TODO: Use getrecipe call for this
        final Recipe result = recipesRepository.findById(recipeId.toString())
                                .map(recipe -> {
                                    updateRecipe.setRecipeId(recipeId.toString());
                                    return recipe = updateRecipe;
                                })
                                .orElseThrow(
                                        () -> new RecipeNotFoundException("Unable to find any recipes with id: " + recipeId.toString()
                                        ));

        return recipesRepository.save(result);
    }

    @Override
    public void deleteRecipe(final UUID recipeId) {
        final Recipe recipe = this.getRecipe(recipeId).orElseThrow( () -> new RecipeNotFoundException("Unable to find any recipes with id: " + recipeId.toString()));
        imageService.removeImageFromBucket(recipe.getImage());
        recipesRepository.deleteById(recipeId.toString());
    }
}
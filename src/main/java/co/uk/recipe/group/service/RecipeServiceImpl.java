package co.uk.recipe.group.service;

import co.uk.recipe.group.dao.RecipesRepository;
import co.uk.recipe.group.domain.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class RecipeServiceImpl implements RecipeService{

    @Autowired
    RecipesRepository recipesRepository;

    @Autowired
    ImageService imageService;

    @Override
    public Recipe addRecipe(Recipe recipe, MultipartFile multipartFile) throws IOException {

        final Recipe createdRecipe = recipesRepository.save(recipe);

        final String imageUrl = imageService.uploadImageToBucket(multipartFile);

        createdRecipe.setImage(imageUrl);
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
}
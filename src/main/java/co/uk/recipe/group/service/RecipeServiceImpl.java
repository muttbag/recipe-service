package co.uk.recipe.group.service;

import co.uk.recipe.group.dao.RecipesRepository;
import co.uk.recipe.group.domain.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RecipeServiceImpl implements RecipeService{

    @Autowired
    RecipesRepository recipesRepository;

    @Override
    public Recipe addRecipe(Recipe recipe) {
        return recipesRepository.save(recipe);
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
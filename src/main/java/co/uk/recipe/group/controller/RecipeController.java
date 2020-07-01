package co.uk.recipe.group.controller;

import co.uk.recipe.group.domain.Recipe;
import co.uk.recipe.group.error.RecipeNotFoundException;
import co.uk.recipe.group.message.CreateRecipeRequest;
import co.uk.recipe.group.service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    //TODO: Add delete for deleting recipe
    //TODO: Edit CreateRecipeRequest to remove image from request

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Recipe addRecipeWithFile(@RequestPart("recipe") @Valid  CreateRecipeRequest createRecipe, @RequestPart("image") MultipartFile multipartFile) throws IOException {

        final Recipe recipe = new Recipe();
        BeanUtils.copyProperties(createRecipe, recipe);

        return recipeService.addRecipe(recipe, multipartFile);
    }

    @GetMapping(value = "/{recipeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Recipe getRecipe(@PathVariable @NotNull final UUID recipeId) {

        return recipeService.getRecipe(recipeId).orElseThrow(() -> new RecipeNotFoundException("Unable to find any recipes with id: " + recipeId.toString()));

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @PutMapping(value = "/{recipeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Recipe updateRecipe(@PathVariable @NotNull final UUID recipeId, @RequestBody @Valid CreateRecipeRequest createRecipe) {

        final Recipe recipe = new Recipe();
        BeanUtils.copyProperties(createRecipe, recipe);

        return recipeService.updateRecipe(recipeId, recipe);
    }

    @DeleteMapping(value = "/{recipeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteRecipe(@PathVariable @NotNull final UUID recipeId) {
        recipeService.deleteRecipe(recipeId);

        return ResponseEntity.noContent().build();
    }
}

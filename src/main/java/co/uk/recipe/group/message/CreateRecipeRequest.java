package co.uk.recipe.group.message;

import co.uk.recipe.group.domain.Difficulty;

import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;

public class CreateRecipeRequest {

    @NotNull(message = "userId field required")
    private Integer userId;

    @NotBlank(message = "A recipe name is required" )
    private String name;

    @NotEmpty(message = "A list of ingredients is required")
    private Map<String, String> ingredients;

    @NotEmpty(message = "A list of steps to cook the recipe is required")
    private List<String> steps;

    @Min(value = 1, message = "Time to cook (in minutes) is required")
    private Integer timeToCook;

    //Change to String, ENUM easy, medium and hard
    private Difficulty difficulty;

    @NotNull(message = "cuisine type is required")
    private String cuisine;

    @NotNull(message = "URL to finished image of the meal is required")
    private String image;

    @Min(value = 1, message = "Number of people the meal is for required")
    private Integer people;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Map<String, String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public Integer getTimeToCook() {
        return timeToCook;
    }

    public void setTimeToCook(Integer timeToCook) {
        this.timeToCook = timeToCook;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }
}

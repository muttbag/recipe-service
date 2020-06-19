package co.uk.recipe.group.controller;

import co.uk.recipe.group.domain.Recipe;
import co.uk.recipe.group.error.RecipeNotFoundException;
import co.uk.recipe.group.message.CreateRecipeRequest;
import co.uk.recipe.group.service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    //TODO: Add PUT for updating recipe

//    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public Recipe addRecipe(@Valid @RequestBody CreateRecipeRequest createRecipe) {
//
//        //Needs the ability to add image and upload to S3 bucket.
//        final Recipe recipe = new Recipe();
//
//        BeanUtils.copyProperties(createRecipe, recipe);
//
//        return recipeService.addRecipe(recipe);
//    }

//    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = "multipart/form-data")
//    public Recipe addRecipeWithFile(@ModelAttribute FormWrapper model) {
    @RequestMapping( method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Recipe addRecipeWithFile(@RequestPart("recipe") @Valid  CreateRecipeRequest createRecipe, @RequestPart("image") MultipartFile multipartFile) throws IOException {

        //Needs the ability to add image and upload to S3 bucket.
        final Recipe recipe = new Recipe();

        BeanUtils.copyProperties(createRecipe, recipe);

        return recipeService.addRecipe(recipe, multipartFile);
    }

    @GetMapping(value = "/{recipeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Recipe getRecipe(@PathVariable @NotNull final UUID recipeId) {

        final Recipe result = recipeService.getRecipe(recipeId).orElseThrow(() -> new RecipeNotFoundException("Unable to find any recipes with id: " + recipeId.toString()));

        return result;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }


    @GetMapping(value = "/old", produces = MediaType.APPLICATION_JSON_VALUE)
    public String test() {
        return "{\n" +
                "\t\"recipes\": [{\n" +
                "\t\t\t\"userId\": 2,\n" +
                "\t\t\t\"recipeId\": 3,\n" +
                "\t\t\t\"name\": \"Huevos Rancheros\",\n" +
                "\t\t\t\"ingredients\": {\n" +
                "\t\t\t\t\"olive oil\": \"2 tbsp\",\n" +
                "\t\t\t\t\"onion\": \"1\",\n" +
                "\t\t\t\t\"garlic cloves\": \"2\",\n" +
                "\t\t\t\t\"red kidney beans\": \"400g\",\n" +
                "\t\t\t\t\"ground cumin\": \"1 tbsp\",\n" +
                "\t\t\t\t\"chilli powder\": \"1/4 tsp\",\n" +
                "\t\t\t\t\"dried oregano\": \"1/2 tsp\",\n" +
                "\t\t\t\t\"eggs\": \"4\",\n" +
                "\t\t\t\t\"small flour tortillas\": \"4\",\n" +
                "\t\t\t\t\"tomato\": \"1\",\n" +
                "\t\t\t\t\"jalapeño peppers\": \"3\",\n" +
                "\t\t\t\t\"chedder\": \"30g\",\n" +
                "\t\t\t\t\"avocado\": \"1\",\n" +
                "\t\t\t\t\"lime\": \"1\",\n" +
                "\t\t\t\t\"coriander\": \"10g\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"steps\": [\"Heat 1 tbsp oil in a large pan. Add the onions with a pinch of salt, and cook until translucent, around 3-4 mins. Add the garlic and cook for a minute more.\", \"Stir in the beans, cumin, chilli powder, oregano, some seasoning and 100ml water. Cook for 5-7 mins, stirring occasionally, or until the beans have softened, then remove from the heat, mash and set aside.\", \"Heat the remaining oil in a large frying pan over a medium-high heat. Crack in the eggs, then reduce the heat to low and cook slowly until the whites are completely firm\", \"To assemble, spread the beans onto the tortillas, add the tomatoes and jalapeños and sprinkle with cheese. Top with some avocado, a squeeze of lime juice and a fried egg, then scatter with coriander. Serve with the lime wedges on the side.\"],\n" +
                "\t\t\t\"likes\": 4,\n" +
                "\t\t\t\"timeToCook\": 0.5,\n" +
                "\t\t\t\"difficulty\": 2,\n" +
                "\t\t\t\"cuisine\": \"Mexican\",\n" +
                "\t\t\t\"image\": \"https://recipe-bucket-09062020.s3.eu-west-2.amazonaws.com/huevos-rancheros.jpg\",\n" +
                "\t\t\t\"people\": \"2\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"userId\": 1,\n" +
                "\t\t\t\"recipeId\": 1,\n" +
                "\t\t\t\"name\": \"Cottage Pie\",\n" +
                "\t\t\t\"ingredients\": {\n" +
                "\t\t\t\t\"olive oil\": \"3 tbsp\",\n" +
                "\t\t\t\t\"beef mince\": \"1250g\",\n" +
                "\t\t\t\t\"carrots\": \"3\",\n" +
                "\t\t\t\t\"celery sticks\": \"2\",\n" +
                "\t\t\t\t\"garlic cloves\": \"2\",\n" +
                "\t\t\t\t\"plain flour\": \"3 tbsp\",\n" +
                "\t\t\t\t\"onions\": \"2\",\n" +
                "\t\t\t\t\"red wine\": \"1 large glass\",\n" +
                "\t\t\t\t\"beef stock\": \"850ml\",\n" +
                "\t\t\t\t\"worcestershire sauce\": \"4 tbsp\",\n" +
                "\t\t\t\t\"thyme\": \"3\",\n" +
                "\t\t\t\t\"bay leaves\": \"3\",\n" +
                "\t\t\t\t\"potatoes\": \"1800g\",\n" +
                "\t\t\t\t\"milk\": \"225ml\",\n" +
                "\t\t\t\t\"butter\": \"25g\",\n" +
                "\t\t\t\t\"chedder\": \"200g\",\n" +
                "\t\t\t\t\"nutmeg\": \"10g\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"steps\": [\"Heat 1 tbsp olive oil in a large saucepan and fry 1¼ kg beef mince until browned – you may need to do this in batches. Set aside as it browns.\", \"Put the other 2 tbsp olive oil into the pan, add 2 finely chopped onions, 3 chopped carrots and 3 chopped celery sticks and cook on a gentle heat until soft, about 20 mins.\", \"Add 2 finely chopped garlic cloves, 3 tbsp plain flour and 1 tbsp tomato purée, increase the heat and cook for a few mins, then return the beef to the pan.\", \"Pour over a large glass of red wine, if using, and boil to reduce it slightly before adding the 850ml beef stock, 4 tbsp Worcestershire sauce, a few thyme sprigs and 2 bay leaves.\", \"Bring to a simmer and cook, uncovered, for 45 mins. By this time the gravy should be thick and coating the meat. Check after about 30 mins – if a lot of liquid remains, increase the heat slightly to reduce the gravy a little. Season well, then discard the bay leaves and thyme stalks.\", \"Meanwhile, make the mash. In a large saucepan, cover the 1.8kg potatoes which you've peeled and chopped, in salted cold water, bring to the boil and simmer until tender.\", \"Drain well, then allow to steam-dry for a few mins. Mash well with the 225ml milk, 25g butter, and three-quarters of the 200g strong cheddar cheese, then season with freshly grated nutmeg and some salt and pepper.\", \"Spoon the meat into 2 ovenproof dishes. Pipe or spoon on the mash to cover. Sprinkle on the remaining cheese.\", \"If eating straight away, heat oven to 220C/200C fan/gas 7 and cook for 25-30 mins, or until the topping is golden.\", \"If you want to use a slow cooker, brown your mince in batches then tip into your slow cooker and stir in the vegetables, flour, purée, wine, stock, Worcestershire sauce and herbs with some seasoning. Cover and cook on High for 4-5 hours. Make the mash following the previous steps, and then oven cook in the same way to finish.\"],\n" +
                "\t\t\t\"likes\": 2,\n" +
                "\t\t\t\"timeToCook\": 1.5,\n" +
                "\t\t\t\"difficulty\": 3,\n" +
                "\t\t\t\"cuisine\": \"English\",\n" +
                "\t\t\t\"image\": \"https://recipe-bucket-09062020.s3.eu-west-2.amazonaws.com/shepherds-pie.jpg\",\n" +
                "\t\t\t\"people\": \"5\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"userId\": 1,\n" +
                "\t\t\t\"recipeId\": 2,\n" +
                "\t\t\t\"name\": \"Beef & Aubergine Hotpot\",\n" +
                "\t\t\t\"ingredients\": {\n" +
                "\t\t\t\t\"star anise\": \"5\",\n" +
                "\t\t\t\t\"cinnamon stick\": \"1\",\n" +
                "\t\t\t\t\"sunflower oil\": \"3 tbsp\",\n" +
                "\t\t\t\t\"braising steak\": \"2000g\",\n" +
                "\t\t\t\t\"Thai fish sauce\": \"2 tbsp\",\n" +
                "\t\t\t\t\"soy sauce\": \"200ml\",\n" +
                "\t\t\t\t\"galangal\": \"100g\",\n" +
                "\t\t\t\t\"demerara sugar\": \"1 tbsp\",\n" +
                "\t\t\t\t\"kaffir lime leaves\": \"6\",\n" +
                "\t\t\t\t\"aubergines\": \"3\",\n" +
                "\t\t\t\t\"rice and coriander\": \"2 portions\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"steps\": [\"Heat oven to 150C/130C fan/gas 2. Heat a large casserole dish, toast the star anise and cinnamon until fragrant, then scoop out of the dish. Add the oil, then brown the beef in batches (be careful not to overcrowd the pan). Set the meat aside on a plate.\", \"Add the rest of the ingredients (except for the aubergine) to the casserole dish, stir well and bring to the boil. Add the spices and meat back in, skim off any fat that comes to the surface, then cover the casserole and cook in the oven for 2 hrs.\", \"Remove the lid, add the aubergine, and give everything a good stir, then return to the oven and cook for 1 hr uncovered until the aubergine and meat are tender. Rest until cool enough to eat. Scatter over the coriander and sliced red chilli, then serve with rice.\"],\n" +
                "\t\t\t\"likes\": 4,\n" +
                "\t\t\t\"timeToCook\": 3.5,\n" +
                "\t\t\t\"difficulty\": 2,\n" +
                "\t\t\t\"cuisine\": \"Chinese\",\n" +
                "\t\t\t\"image\": \"https://recipe-bucket-09062020.s3.eu-west-2.amazonaws.com/beef-aubergine-hotpot.jpg\",\n" +
                "\t\t\t\"people\": \"2\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";
    }

    @GetMapping(value = "/old/1", produces = MediaType.APPLICATION_JSON_VALUE)
    public String test1() {
        return "{\n" +
                "\t\"userId\": 1,\n" +
                "\t\"recipeId\": 1,\n" +
                "\t\"name\": \"Cottage Pie\",\n" +
                "\t\"ingredients\": {\n" +
                "\t\t\"olive oil\": \"3 tbsp\",\n" +
                "\t\t\"beef mince\": \"1250g\",\n" +
                "\t\t\"carrots\": \"3\",\n" +
                "\t\t\"celery sticks\": \"2\",\n" +
                "\t\t\"garlic cloves\": \"2\",\n" +
                "\t\t\"plain flour\": \"3 tbsp\",\n" +
                "\t\t\"onions\": \"2\",\n" +
                "\t\t\"red wine\": \"1 large glass\",\n" +
                "\t\t\"beef stock\": \"850ml\",\n" +
                "\t\t\"worcestershire sauce\": \"4 tbsp\",\n" +
                "\t\t\"thyme\": \"3\",\n" +
                "\t\t\"bay leaves\": \"3\",\n" +
                "\t\t\"potatoes\": \"1800g\",\n" +
                "\t\t\"milk\": \"225ml\",\n" +
                "\t\t\"butter\": \"25g\",\n" +
                "\t\t\"chedder\": \"200g\",\n" +
                "\t\t\"nutmeg\": \"10g\"\n" +
                "\t},\n" +
                "\t\"steps\": [\"Heat 1 tbsp olive oil in a large saucepan and fry 1¼ kg beef mince until browned – you may need to do this in batches. Set aside as it browns.\", \"Put the other 2 tbsp olive oil into the pan, add 2 finely chopped onions, 3 chopped carrots and 3 chopped celery sticks and cook on a gentle heat until soft, about 20 mins.\", \"Add 2 finely chopped garlic cloves, 3 tbsp plain flour and 1 tbsp tomato purée, increase the heat and cook for a few mins, then return the beef to the pan.\", \"Pour over a large glass of red wine, if using, and boil to reduce it slightly before adding the 850ml beef stock, 4 tbsp Worcestershire sauce, a few thyme sprigs and 2 bay leaves.\", \"Bring to a simmer and cook, uncovered, for 45 mins. By this time the gravy should be thick and coating the meat. Check after about 30 mins – if a lot of liquid remains, increase the heat slightly to reduce the gravy a little. Season well, then discard the bay leaves and thyme stalks.\", \"Meanwhile, make the mash. In a large saucepan, cover the 1.8kg potatoes which you've peeled and chopped, in salted cold water, bring to the boil and simmer until tender.\", \"Drain well, then allow to steam-dry for a few mins. Mash well with the 225ml milk, 25g butter, and three-quarters of the 200g strong cheddar cheese, then season with freshly grated nutmeg and some salt and pepper.\", \"Spoon the meat into 2 ovenproof dishes. Pipe or spoon on the mash to cover. Sprinkle on the remaining cheese.\", \"If eating straight away, heat oven to 220C/200C fan/gas 7 and cook for 25-30 mins, or until the topping is golden.\", \"If you want to use a slow cooker, brown your mince in batches then tip into your slow cooker and stir in the vegetables, flour, purée, wine, stock, Worcestershire sauce and herbs with some seasoning. Cover and cook on High for 4-5 hours. Make the mash following the previous steps, and then oven cook in the same way to finish.\"],\n" +
                "\t\"likes\": 2,\n" +
                "\t\"timeToCook\": 1.5,\n" +
                "\t\"difficulty\": 3,\n" +
                "\t\"cuisine\": \"English\",\n" +
                "\t\"image\": \"https://recipe-bucket-09062020.s3.eu-west-2.amazonaws.com/shepherds-pie.jpg\",\n" +
                "\t\"people\": \"5\"\n" +
                "}";
    }

    @GetMapping(value = "/old/2", produces = MediaType.APPLICATION_JSON_VALUE)
    public String test2() {
        return "{\n" +
                "\t\"userId\": 1,\n" +
                "\t\"recipeId\": 2,\n" +
                "\t\"name\": \"Beef & Aubergine Hotpot\",\n" +
                "\t\"ingredients\": {\n" +
                "\t\t\"star anise\": \"5\",\n" +
                "\t\t\"cinnamon stick\": \"1\",\n" +
                "\t\t\"sunflower oil\": \"3 tbsp\",\n" +
                "\t\t\"braising steak\": \"2000g\",\n" +
                "\t\t\"Thai fish sauce\": \"2 tbsp\",\n" +
                "\t\t\"soy sauce\": \"200ml\",\n" +
                "\t\t\"galangal\": \"100g\",\n" +
                "\t\t\"demerara sugar\": \"1 tbsp\",\n" +
                "\t\t\"kaffir lime leaves\": \"6\",\n" +
                "\t\t\"aubergines\": \"3\",\n" +
                "\t\t\"rice and coriander\": \"2 portions\"\n" +
                "\t},\n" +
                "\t\"steps\": [\"Heat oven to 150C/130C fan/gas 2. Heat a large casserole dish, toast the star anise and cinnamon until fragrant, then scoop out of the dish. Add the oil, then brown the beef in batches (be careful not to overcrowd the pan). Set the meat aside on a plate.\", \"Add the rest of the ingredients (except for the aubergine) to the casserole dish, stir well and bring to the boil. Add the spices and meat back in, skim off any fat that comes to the surface, then cover the casserole and cook in the oven for 2 hrs.\", \"Remove the lid, add the aubergine, and give everything a good stir, then return to the oven and cook for 1 hr uncovered until the aubergine and meat are tender. Rest until cool enough to eat. Scatter over the coriander and sliced red chilli, then serve with rice.\"],\n" +
                "\t\"likes\": 4,\n" +
                "\t\"timeToCook\": 3.5,\n" +
                "\t\"difficulty\": 2,\n" +
                "\t\"cuisine\": \"Chinese\",\n" +
                "\t\"image\": \"https://recipe-bucket-09062020.s3.eu-west-2.amazonaws.com/beef-aubergine-hotpot.jpg\",\n" +
                "\t\"people\": \"2\"\n" +
                "}";
    }

    @GetMapping(value = "/old/3", produces = MediaType.APPLICATION_JSON_VALUE)
    public String test3() {
        return "{\n" +
                "\t\"userId\": 2,\n" +
                "\t\"recipeId\": 3,\n" +
                "\t\"name\": \"Huevos Rancheros\",\n" +
                "\t\"ingredients\": {\n" +
                "\t\t\"olive oil\": \"2 tbsp\",\n" +
                "\t\t\"onion\": \"1\",\n" +
                "\t\t\"garlic cloves\": \"2\",\n" +
                "\t\t\"red kidney beans\": \"400g\",\n" +
                "\t\t\"ground cumin\": \"1 tbsp\",\n" +
                "\t\t\"chilli powder\": \"1/4 tsp\",\n" +
                "\t\t\"dried oregano\": \"1/2 tsp\",\n" +
                "\t\t\"eggs\": \"4\",\n" +
                "\t\t\"small flour tortillas\": \"4\",\n" +
                "\t\t\"tomato\": \"1\",\n" +
                "\t\t\"jalapeño peppers\": \"3\",\n" +
                "\t\t\"chedder\": \"30g\",\n" +
                "\t\t\"avocado\": \"1\",\n" +
                "\t\t\"lime\": \"1\",\n" +
                "\t\t\"coriander\": \"10g\"\n" +
                "\t},\n" +
                "\t\"steps\": [\"Heat 1 tbsp oil in a large pan. Add the onions with a pinch of salt, and cook until translucent, around 3-4 mins. Add the garlic and cook for a minute more.\", \"Stir in the beans, cumin, chilli powder, oregano, some seasoning and 100ml water. Cook for 5-7 mins, stirring occasionally, or until the beans have softened, then remove from the heat, mash and set aside.\", \"Heat the remaining oil in a large frying pan over a medium-high heat. Crack in the eggs, then reduce the heat to low and cook slowly until the whites are completely firm\", \"To assemble, spread the beans onto the tortillas, add the tomatoes and jalapeños and sprinkle with cheese. Top with some avocado, a squeeze of lime juice and a fried egg, then scatter with coriander. Serve with the lime wedges on the side.\"],\n" +
                "\t\"likes\": 4,\n" +
                "\t\"timeToCook\": 0.5,\n" +
                "\t\"difficulty\": 2,\n" +
                "\t\"cuisine\": \"Mexican\",\n" +
                "\t\"image\": \"https://recipe-bucket-09062020.s3.eu-west-2.amazonaws.com/huevos-rancheros.jpg\",\n" +
                "\t\"people\": \"2\"\n" +
                "}";
    }
}

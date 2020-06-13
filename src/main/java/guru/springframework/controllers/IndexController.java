package guru.springframework.controllers;

import guru.springframework.domain.Category;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repos.CategoryRepository;
import guru.springframework.repos.UnitOfMeasureRepository;
import guru.springframework.services.RecipeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.Set;

/**
 * Created by jt on 6/1/17.
 */
@Controller
@Slf4j
public class IndexController {

    private final RecipeServiceImpl recipeService;

    public IndexController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }
/*

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(){
        return "index";
    }
*/

    @RequestMapping({"", "/", "/index"})
    public String getAllRecipes(Model model) {
        Set<Recipe> recipes = recipeService.findAllRecipes();
        recipes.stream().findFirst().ifPresent(recipe -> log.debug("This is the first recipe: " + recipe.toString()));
        model.addAttribute("recipes", recipes);
        return "recipes";
    }
}

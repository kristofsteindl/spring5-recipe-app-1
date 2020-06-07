package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repos.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Iterable<Recipe> findAllRecipes() {
        return recipeRepository.findAll();
    }

}

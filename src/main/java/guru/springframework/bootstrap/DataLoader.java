package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repos.CategoryRepository;
import guru.springframework.repos.RecipeRepository;
import guru.springframework.repos.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class DataLoader implements CommandLineRunner {

    private static Category defaultCategory;
    private static UnitOfMeasure defaultUnitOfMeasure;

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeRepository recipeRepository;

    public DataLoader(
            CategoryRepository categoryRepository,
            UnitOfMeasureRepository unitOfMeasureRepository,
            RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.debug("DataLoader starting...");
        initDefaultCategory();
        initDefaultUnitOfMeasurments();

        createGuacamole();
        createTacos();
    }

    private void createTacos() {
        Recipe tacos = new Recipe();
        tacos.setDescription("picy grilled chicken tacos");
        //tacos.setDescription("Spicy grilled chicken tacos! Quick marinade, then grill. Ready in about 30 minutes. Great for a quick weeknight dinner, backyard cookouts, and tailgate parties.");        tacos.setPrepTime(10);
        tacos.setPrepTime(20);
        tacos.setCookTime(4);
        tacos.setServings(5);
        tacos.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        tacos.setDifficulty(Difficulty.MODERATE);
        tacos.setDirections(getTacosDirections());

        Notes tacosNotes = getNotes(getTacosRecipeNotes());
        tacosNotes.setRecipe(tacos);
        tacos.setNotes(tacosNotes);

        setTacosIngredients(tacos);
        setTacosCategories(tacos);

        recipeRepository.save(tacos);
    }

    private void setTacosIngredients(Recipe tacos) {
        Ingredient i1 = new Ingredient();
        i1.setAmount(new BigDecimal(2));
        i1.setDescription("ancho chili powder");
        i1.setUom(unitOfMeasureRepository.findByDescription("tablespoon").orElse(defaultUnitOfMeasure));
        tacos.addIngredient(i1);

        Ingredient i2 = new Ingredient();
        i2.setAmount(new BigDecimal(1));
        i2.setDescription("dried oregano");
        i2.setUom(unitOfMeasureRepository.findByDescription("teaspoon").orElse(defaultUnitOfMeasure));
        tacos.addIngredient(i2);

        Ingredient i3 = new Ingredient();
        i3.setAmount(new BigDecimal(1));
        i3.setDescription("dried cumin");
        i3.setUom(unitOfMeasureRepository.findByDescription("tablespoon").orElse(defaultUnitOfMeasure));
        tacos.addIngredient(i3);

        Ingredient i4 = new Ingredient();
        i4.setAmount(new BigDecimal(1));
        i4.setDescription("sugar");
        i4.setUom(unitOfMeasureRepository.findByDescription("tablespoon").orElse(defaultUnitOfMeasure));
        tacos.addIngredient(i4);

        Ingredient i9 = new Ingredient();
        i9.setAmount(new BigDecimal(0.5));
        i9.setDescription("salt");
        i9.setUom(unitOfMeasureRepository.findByDescription("teaspoon").orElse(defaultUnitOfMeasure));
        tacos.addIngredient(i9);

        Ingredient i10 = new Ingredient();
        i10.setAmount(new BigDecimal(1));
        i10.setDescription("garlic, finely chopped");
        i10.setUom(unitOfMeasureRepository.findByDescription("clove").orElse(defaultUnitOfMeasure));
        tacos.addIngredient(i10);


        Ingredient i5 = new Ingredient();
        i5.setAmount(new BigDecimal(8));
        i5.setDescription("small corn tortillas");
        i5.setUom(unitOfMeasureRepository.findByDescription("").orElse(defaultUnitOfMeasure));
        tacos.addIngredient(i5);

        Ingredient i6 = new Ingredient();
        i6.setAmount(new BigDecimal(3));
        i6.setDescription("packed baby arugula (3 ounces)");
        i6.setUom(unitOfMeasureRepository.findByDescription("cup").orElse(defaultUnitOfMeasure));
        tacos.addIngredient(i6);

        Ingredient i7 = new Ingredient();
        i7.setAmount(new BigDecimal(1));
        i7.setDescription("freshly grated black pepper");
        i7.setUom(unitOfMeasureRepository.findByDescription("dash").orElse(defaultUnitOfMeasure));
        tacos.addIngredient(i7);

        Ingredient i8 = new Ingredient();
        i8.setAmount(new BigDecimal(4));
        i8.setDescription("radishes, thinly sliced");
        i8.setUom(unitOfMeasureRepository.findByDescription("").orElse(defaultUnitOfMeasure));
        tacos.addIngredient(i8);

    }

    private void setTacosCategories(Recipe guacamole) {
        Category fastFood = categoryRepository.findByDescription("Fast Food").orElse(defaultCategory);
        Category mexican = categoryRepository.findByDescription("Mexican").orElse(defaultCategory);
        guacamole.getCategories().add(fastFood);
        guacamole.getCategories().add(mexican);
    }


    private String getTacosRecipeNotes() {
        String recipeNotes = "We have a family motto and it is this: Everything goes better in a tortilla.\n" + "\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. " +
                "I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.";
        return recipeNotes;
    }

    private String getTacosDirections() {
        String tacosDirections = new StringBuilder("1 Prepare a gas or charcoal grill for medium-high, direct heat.")
                .append("2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.")
                .append("Set aside to marinate while the grill heats and you prepare the rest of the toppings.")
                .append("3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.")
                .append("4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.")
                .append("Wrap warmed tortillas in a tea towel to keep them warm until serving.")
                .append("5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.")
                .toString();
        return tacosDirections;
    }

    private void createGuacamole() {
        Recipe guacamole = new Recipe();
        guacamole.setDescription("Perfect Guacamole");
        //guacamole.setDescription("The best guacamole keeps it simple: just ripe avocados, salt, a squeeze of lime, onions, chiles, cilantro, and some chopped tomato. Serve it as a dip at your next party or spoon it on top of tacos for an easy dinner upgrade.");
        guacamole.setPrepTime(10);
        guacamole.setCookTime(0);
        guacamole.setServings(4);
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setDirections(getGuacamoleDirections());

        Notes guacamoleNotes = getNotes(getGuacalomeRecipeNotes());
        guacamole.setNotes(guacamoleNotes);

        setGuacalomeIngredients(guacamole);
        setGuacalomeCategories(guacamole);

        recipeRepository.save(guacamole);
    }

    private void setGuacalomeIngredients(Recipe guacamole) {
        Ingredient i1 = new Ingredient();
        i1.setAmount(new BigDecimal(2));
        i1.setDescription("ripe avocados");
        i1.setUom(unitOfMeasureRepository.findByDescription("each").orElse(defaultUnitOfMeasure));
        guacamole.addIngredient(i1);


        Ingredient i2 = new Ingredient();
        i2.setAmount(new BigDecimal(0.25));
        i2.setDescription("salt");
        i2.setUom(unitOfMeasureRepository.findByDescription("teaspoon").orElse(defaultUnitOfMeasure));
        guacamole.addIngredient(i2);


        Ingredient i3 = new Ingredient();
        i3.setAmount(new BigDecimal(1));
        i3.setDescription("fresh lime juice or lemon juice");
        i3.setUom(unitOfMeasureRepository.findByDescription("tablespoon").orElse(defaultUnitOfMeasure));
        guacamole.addIngredient(i3);


        Ingredient i4 = new Ingredient();
        i4.setAmount(new BigDecimal(2));
        i4.setDescription("minced red onion or thinly sliced green onion");
        i4.setUom(unitOfMeasureRepository.findByDescription("tablespoon").orElse(defaultUnitOfMeasure));
        guacamole.addIngredient(i4);


        Ingredient i5 = new Ingredient();
        i5.setAmount(new BigDecimal(1.5));
        i5.setDescription("serrano chiles, stems and seeds removed, minced");
        i5.setUom(unitOfMeasureRepository.findByDescription("").orElse(defaultUnitOfMeasure));
        guacamole.addIngredient(i5);


        Ingredient i6 = new Ingredient();
        i6.setAmount(new BigDecimal(2));
        i6.setDescription("cilantro (leaves and tender stems), finely chopped");
        i6.setUom(unitOfMeasureRepository.findByDescription("tablespoon").orElse(defaultUnitOfMeasure));
        guacamole.addIngredient(i6);


        Ingredient i7 = new Ingredient();
        i7.setAmount(new BigDecimal(1));
        i7.setDescription("freshly grated black pepper");
        i7.setUom(unitOfMeasureRepository.findByDescription("dash").orElse(defaultUnitOfMeasure));
        guacamole.addIngredient(i7);


        Ingredient i8 = new Ingredient();
        i8.setAmount(new BigDecimal(0.5));
        i8.setDescription("ripe tomato, seeds and pulp removed, chopped");
        i8.setUom(unitOfMeasureRepository.findByDescription("").orElse(defaultUnitOfMeasure));
        guacamole.addIngredient(i8);


        Ingredient i9 = new Ingredient();
        i9.setAmount(new BigDecimal(1));
        i9.setDescription("Red radishes or jicama, to garnish");
        i9.setUom(unitOfMeasureRepository.findByDescription("").orElse(defaultUnitOfMeasure));
        guacamole.addIngredient(i9);


        Ingredient i10 = new Ingredient();
        i10.setAmount(new BigDecimal(1));
        i10.setDescription("Tortilla chips, to serve");
        i10.setUom(unitOfMeasureRepository.findByDescription("").orElse(defaultUnitOfMeasure));
        guacamole.addIngredient(i10);


    }

    private void setGuacalomeCategories(Recipe guacamole) {
        Category american = categoryRepository.findByDescription("American").orElse(defaultCategory);
        Category mexican = categoryRepository.findByDescription("Mexican").orElse(defaultCategory);
        guacamole.getCategories().add(american);
        guacamole.getCategories().add(mexican);
    }


    private String getGuacalomeRecipeNotes() {
        String recipeNotes = "Guacamole! Did you know that over 2 billion pounds of avocados are consumed each year in the U.S.? (Google it.) That’s over 7 pounds per person. I’m guessing that most of those avocados go into what has become America’s favorite dip, guacamole.";
        return recipeNotes;
    }

    private String getGuacamoleDirections() {
        String guacamoleDirections = new StringBuilder("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.")
                .append("2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.")
                .append("3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.")
                .append("Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.")
                .append("Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.")
                .append("4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.")
                .toString();
        return guacamoleDirections;
    }


    private Notes getNotes(String recipteNotes) {
        Notes notes = new Notes();
        notes.setRecipeNotes(recipteNotes);
        return notes;
    }

    private void initDefaultUnitOfMeasurments() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription("");
        defaultUnitOfMeasure = unitOfMeasureRepository.save(unitOfMeasure);
        System.out.println(defaultUnitOfMeasure);
    }

    private void initDefaultCategory() {
        Category newDefaultCategory = new Category();
        newDefaultCategory.setDescription("Default Category");
        defaultCategory = categoryRepository.save(newDefaultCategory);
    }
}

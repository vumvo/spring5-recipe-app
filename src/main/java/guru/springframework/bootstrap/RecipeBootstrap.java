package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository){
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes(){
        List<Recipe> recipes = new ArrayList<>();

        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
        if(!eachUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if (!tableSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if (!teaSpoonUomOptional.isPresent()) {
            throw new RuntimeException(("Expected UOM not found"));
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
        if (!dashUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }
        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");
        if (!pintUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }
        Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");
        if (!cupUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        //get optionals
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teaSpoonUom = teaSpoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure cupUom = cupUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();

        //get Categories
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if (!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category not found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if (!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category not found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        Recipe guacamoleRecipe = Recipe.builder()
                .description("Perfect Guacamole")
                .difficulty(Difficulty.EASY)
                .prepTime(10)
                .cookTime(1)
                .direction("Guacamole is best eaten right after it’s made. Like apples, avocados start to oxidize and turn brown once they’ve been cut. That said, the acid in the lime juice you add to guacamole can help slow down that process, and if you store the guacamole properly, you can easily make it a few hours ahead if you are preparing for a party.\n" +
                        "\n" +
                        "The trick to keeping guacamole green is to make sure air doesn’t touch it! Transfer it to a container, cover with plastic wrap, and press down on the plastic wrap to squeeze out any air pockets. Make sure any exposed surface of the guacamole is touching the plastic wrap, not air. This will keep the amount of browning to a minimum.\n" +
                        "\n" +
                        "You can store the guacamole in the fridge this way for up to three days.\n" +
                        "\n" +
                        "If you leave the guacamole exposed to air, it will start to brown and discolor. That browning isn’t very appetizing, but the guacamole is still good. You can either scrape off the brown parts and discard, or stir them into the rest of the guacamole.")
                .ingredient(Ingredient.builder().description("ripe avocados").amount(new BigDecimal(2)).uom(eachUom).build())
                .ingredient(Ingredient.builder().description("Koder salt").amount(new BigDecimal(".5")).uom(teaSpoonUom).build())
                .ingredient(Ingredient.builder().description("fresh lime juice or lemon juice").amount(new BigDecimal(2)).uom(tableSpoonUom).build())
                .ingredient(Ingredient.builder().description("minced red onion or thinly sliced green onion").amount(new BigDecimal(2)).uom(tableSpoonUom).build())
                .ingredient(Ingredient.builder().description("serrano chiles, stems and seeds removed, minced").amount(new BigDecimal(2)).uom(eachUom).build())
                .ingredient(Ingredient.builder().description("Cilantro").amount(new BigDecimal(2)).uom(tableSpoonUom).build())
                .ingredient(Ingredient.builder().description("freshly grated black pepper").amount(new BigDecimal(2)).uom(dashUom).build())
                .ingredient(Ingredient.builder().description("ripe tomato, seeds and pulp removed, chopped").amount(new BigDecimal(".5")).uom(eachUom).build())
                .category(mexicanCategory).category(americanCategory)
                .notes(Notes.builder().recipeNote("Notes for Guacamole Recipe\n" +
                        "For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                        "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite).").build())
                .build();
        recipes.add(guacamoleRecipe);

        //Yummy Tacos
        Recipe tacosRecipe = Recipe.builder()
                .description("Spicy Grilled Chicken Taco")
                .prepTime(20)
                .cookTime(10)
                .difficulty(Difficulty.MODERATE)
                .direction("1. Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                        "2. Make the marinade and coat the chicken: In a large bowl, stir together the chill powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in 10 minutes\n" +
                        "Set aside to marinate while the grill heats and you prepare the rest of the topping\n" +
                        "3. Grill the chicken" +
                        "4. Warm the tortillas" +
                        "5. Assemble the tacos: Slcie the chicken into strips. On each tortilla, place a small handful of argula. Top with chicken slices, sliced avocado" +
                        "\n" +
                        "\n" +
                        "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixz4jvtrAnNm")
                .notes(Notes.builder().recipeNote("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                        "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickeled jalapenos.").build())
                .ingredient(Ingredient.builder().description("Ancho Chili Powder").amount(new BigDecimal(2)).uom(tableSpoonUom).build())
                .ingredient(Ingredient.builder().description("Dried Oregano").amount(new BigDecimal(1)).uom(teaSpoonUom).build())
                .ingredient(Ingredient.builder().description("Dried Cumin").amount(new BigDecimal(1)).uom(teaSpoonUom).build())
                .ingredient(Ingredient.builder().description("Sugar").amount(new BigDecimal(1)).uom(teaSpoonUom).build())
                .ingredient(Ingredient.builder().description("Salt").amount(new BigDecimal(".5")).uom(teaSpoonUom).build())
                .ingredient(Ingredient.builder().description("Clover of Garlic, Chopped").amount(new BigDecimal(1)).uom(eachUom).build())
                .ingredient(Ingredient.builder().description("packed baby argugula").amount(new BigDecimal(3)).uom(cupUom).build())
                .ingredient(Ingredient.builder().description("cherry tomatoes halved").amount(new BigDecimal(.5)).uom(pintUom).build())
                .category(mexicanCategory)
                .build();
        recipes.add(tacosRecipe);

        return recipes;
    }
}
